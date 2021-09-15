const express = require('express')
const bodyParser = require('body-parser')
const path = require('path')
const open = require('open')

function capture() {
  return new Promise((resolve, reject) => {
    const app = express()
    /** @type {import('http').Server} */
    let server = null
    app.use(express.static(path.dirname(__filename)))
    app.use(bodyParser.urlencoded({ extended: true }))

    app.post('/capture', (req, res) => {
      const meetingId = req.body['meeting-id']
      console.log(meetingId)
      res.redirect('http://localhost:5000/finish.html')
      server.close()
      resolve(meetingId)
    })

    server = app.listen(5000, () => {
      console.log('I am ready at 5000')
      open('http://localhost:5000/index.html')
    })
  })
}

module.exports = capture
