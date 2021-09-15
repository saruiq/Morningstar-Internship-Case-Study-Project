const inquirer = require('inquirer')

async function capture() {
  const anwers = await inquirer.prompt([
    {
      name: 'meetingId',
      message: 'Enter a meeting ID:',
      transformer(input) {
        return input && input.replace(/\s/g, '')
      }
    }
  ])

  return anwers.meetingId
}

module.exports = capture
