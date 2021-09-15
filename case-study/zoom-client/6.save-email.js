const fs = require('fs')
const path = require('path')

function saveEmail(email) {
  const pathToEmailRecipients = path.resolve(process.cwd(), 'email.txt')
  fs.writeFileSync(pathToEmailRecipients, email, {encoding: 'utf8'})
}

module.exports = saveEmail
