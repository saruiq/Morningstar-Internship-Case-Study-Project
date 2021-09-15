const fs = require('fs')
const path = require('path')

function saveChatDownloadLink(downloadLink, token) {
    var linkWithToken = null
    if (downloadLink == null) {
        linkWithToken = "No chat file available for download"
    }
    else {
        linkWithToken = downloadLink + "?access_token=" + token
    }
    const pathToMeetingChat = path.resolve(process.cwd(), 'chat_download_link.txt')
    fs.writeFileSync(pathToMeetingChat, linkWithToken, {encoding: 'utf8'})
}

module.exports = saveChatDownloadLink