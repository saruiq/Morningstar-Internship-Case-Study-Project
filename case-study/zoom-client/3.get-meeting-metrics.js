const axios = require('axios').default

async function api(accessToken, meetingId) {
  const result = await axios.get(`https://api.zoom.us/v2/metrics/meetings/${meetingId}`, {
    headers: {
      'Authorization': `Bearer ${accessToken}`
    }
  })

  return result.data
}

module.exports = api
