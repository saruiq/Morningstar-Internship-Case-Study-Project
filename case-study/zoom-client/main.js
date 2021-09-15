// const captureInput = require('./0.input')
const captureInput = require('./app/server')
const authorize = require('./1.authorization')
const accessToken = require('./2.access-token')
const getMeetingMetrics = require('./3.get-meeting-metrics')
const getMeetingData = require('./4.get-meeting-data')
const getRecordingsData = require('./5.get-recordings-data')
const saveEmail = require('./6.save-email')
const saveMeetingName = require('./7.save-meeting-name')
const saveChatDownloadLink = require('./8.save-chat-download-link')

async function run() {
  const meetingId = await captureInput()

  const authCode = await authorize()
  // console.log(`Authorization successful.\nCode = ${authCode}`)

  const token = await accessToken(authCode)
  // console.log(`Token = ${token}`)

  // const metrics = await getMeetingMetrics(token, meetingId)
  // console.log(`Metrics: ${metrics}`)
  // const hasRecording = metrics.has_recording
  // console.log(`Has recording: ${hasRecording}`)

  // if (!hasRecording) { 
    // This solution will only get the host email and ONLY 
    // for meetings that are NOT recorded
    // const meeting = await getMeetingData(token, meetingId)
    // // console.log(JSON.stringify(meeting, null, 2))
    // // console.log(meeting.host_email)

    // saveEmail(meeting.host_email)
    // console.log(`Saved email ${meeting.host_email}`)
  // }
  // else {
    // The solution below would require all meetings be recorded
    // but can return the CHAT and TRANSCRIPT files and the host email
    const recordings = await getRecordingsData(token, meetingId)
    // console.log(JSON.stringify(recordings, null, 2))
    // console.log(recordings.recording_files)

    const recordingFiles = recordings.recording_files
    var downloadLink = null

    for (const file of recordingFiles) {
      if (file.file_type === 'CHAT') {
        downloadLink = file.download_url
        // console.log(`Download link: ${downloadLink}`)
        break
      }
    }

    const meetingName = `${recordings.topic} ${recordings.start_time}`

    saveEmail(recordings.host_email)
    saveMeetingName(meetingName)
    saveChatDownloadLink(downloadLink, token)
    console.log(`Saved email ${recordings.host_email} and `)
    console.log(`meeting name as ${recordings.topic} ${recordings.start_time} and `)
    console.log(`saved link ${downloadLink}`)
  // }
}

run()
  .finally(() => {
    process.exit(0)
  })
