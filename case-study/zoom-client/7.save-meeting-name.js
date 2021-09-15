const fs = require('fs')
const path = require('path')

function saveMeetingName(topic) {
  const pathToEmailRecipients = path.resolve(process.cwd(), 'meeting_name.txt')
  fs.writeFileSync(pathToEmailRecipients, topic, {encoding: 'utf8'})
}

module.exports = saveMeetingName