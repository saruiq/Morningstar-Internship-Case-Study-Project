const { chromium } = require('playwright-chromium')

/**
 *
 * @param {import('playwright-chromium').BrowserType<import('playwright').Browser>} engine
 */
async function run(engine, callback) {
  const clientId = "" // client ID removed

  const browser = await engine.launch({
    args: ['--disable-dev-shm-usage'],
    headless: false,
    devtools: false
  })

  const context = await browser.newContext({
    bypassCSP: true,
    ignoreHTTPSErrors: true
  })
  context.on('page', async page => {
    page.on('requestfailed', page => {
      const url = page.url()

      const matches = /\?code=(.*)/.exec(url)
      if (matches && matches.length > 1) {
        const code = matches[1]
        browser.close()
        callback(code)
      }
    })
  })

  const page = await context.newPage()
  await page.goto(`https://zoom.us/oauth/authorize?response_type=code&client_id=${clientId}&redirect_uri=https://localhost:3000`, { waitUntil: "networkidle" })
}

module.exports = async () => {
  return new Promise((resolve, reject) => {
    try {
      run(chromium, resolve)
    } catch (error) {
      reject(error)
    }
  })
}
