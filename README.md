# Zoom Quick Summary
## Introduction
```
An open source application created by Morningstar 2021 Summer Tech Interns 
(Sarah Iqbal, Daniel LeVert, Ben Reinkemeyer). 

Zoom Quick Summary simplifies generating sharable online meeting summaries. 
Quick Summary will grab your latest Zoom meeting chat history, parse it, 
and then automatically create and email the summary to the meeting host.
```

## Installation
```
Zoom Quick Summary currently only supports MacOS.

First, Install Java, Node.js, and Playwright/Chromium:
1. Follow the steps here on this site to install Java (if you do not have it): 
    https://www.java.com/en/download/help/mac_install.html
2. Open Terminal on your Mac.
3. Type the following commands to install Node.js (if you do not have it) [or download directly from the Node.js site: https://nodejs.org/en/download/]: 
    brew update
    brew install node
4. Lastly, type the following command to install Playwright with Chromium (https://playwright.dev/docs/intro/):
    npx playwright install

Then Install the Application:
1. Download the 'Install' folder from this repo.
2. Open the Install folder and move the 'Quick-Summary' folder to your user folder 
    (e.g. open Terminal, type "whoami" and press enter to get your username, then enter
    "cd /Users/[your username]", and finally enter "open ." to go directly to your
    user folder).
3. Move the blue 'Quick Summary' app icon to your Applications folder. You can
    also copy the icon to your desktop for quicker access.
```

## Commands and How to Use
```
Commands:
#note, #n = Notes
#action, #a = Action Items
#bold, #b = Bold (Default to Notes)
http = Links
? = Questions (Can be in any order)
```

```
Use:
1. Start a Zoom meeting as the host and press the record button at
    the beginning of the meeting. Ensure that you record to the cloud.
2. During the meeting, type in the meeting chat using the above commands.
3. End your meeting and wait for an email from Zoom that your
    recording has finished processing.
4. After your recording has finished processing, double-click on the 
    Quick Summary app icon.
5. Enter the meeting ID for the meeting you want a summary of and press "Submit".
6. You will be redirected. Login on Zoom's website to the account 
    you used to host. You will use Single Sign-On (SSO) if this is
    a Morningstar account.
7. Click "Authorize" to allow Zoom Quick Summary to access your meeting data.
8. Wait to receive your summary to the email you used to host.
9. Forward the email to any participants or others that might
    need it. Enjoy!
```