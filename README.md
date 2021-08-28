# Dulaney Key Point Application

## About
This application was developed by Shu-Ye Joshua Lin, DHS Key Club Membership Secretary ('20-'21) and Vice President ('21-'22). It uses the <a href="https://github.com/stleary/JSON-java">JSON-Java library by Sean Leary</a> for the parsing and writing of JSON files.

This application was developed completely in Java, and was created for use by members of the Dulaney High School Key Club. It is intended to function with the accompanying information system which was introduced in the 2020-2021 Service Year.

The most recent version of the Java runtime environment is recommended to run the application. This application was created to be ran in the Java SE 16.0.2 runtime environment.


## Installation
In order to install the Dulaney Key Point Application, please follow the steps below.

1. Download the application's jar file <a href="https://github.com/shuyelin06/Dulaney-Key-Club-Points/blob/main/deployment/KeyClubPoints.jar">here</a>
    1. Navigate to the link above
    1. Click the "Download" button on the right side of the file preview.
    1. If your browser blocks the download, click the three dots to the right of the download, and click "Keep" so the download continues.
1. Download the Java SE 16.0.2 runtime environment or later. 
    1. Navigate to <a href = "https://www.oracle.com/java/technologies/javase-jdk16-downloads.html"> this link </a>
    1. Select the appropriate download for your operating system. You may need to create an Oracle account for the download to begin.
    1. Run the downloaded file, and complete the Java set-up wizard. Once you can click the "Finish" button, you should be set.
1. Try running the app by running the Jar file. You can do this by double-clicking the jar file.
1. If an application wnidow opens, you've successfully installed the app! You may now use it to view your points.

### Troubleshooting
If you are having issues opening the application, make sure that you have the most recent version of Java present in your computer.
1. Press the windows key, type in "Command Prompt", and hit enter. In your command prompt, type in the following command and hit enter.
```
java -version
```
1. If you receive an error telling you that 'Java is not recognized as a command', you may need to set up your Java environment variable.
    1. <a href="https://www.csestack.org/java-environment-variables-setup-jdk-installation-guide/#:~:text=Java%20Environment%20Variables%20Setup%20Steps%201%20Right-click%20on,same%20on%20your%20system.%208%20Click%20on%20OK.">This guide (for windows) may help</a>
1. If the version successfully prints, but the runtime environment is below 16.0.2, please follow the installation steps again to redownloading the runtime environment.


## How to Use
In order to view your points, you must enter in a valid password. This password will be provided by your Membership Secretary, so be sure to contact them!

After entering a valid password, select a member from the dropdown, and click "Submit". A new tab should appear on the top with the member's name. In this tab, you can view the member's grade, dues paid, and point information. If you want to view more detailed information on their points, you can click one of the month buttons on the bottom left, which will open a new tab to show the events the member participated in (and the corresponding points) for that month.

If you select a new member while another member's tab is present, their tab will be replaced with the new selected member. There is a visual bug with the name of the new member tab (where the tab's name is a month and not the member's name), but the information is still correct.

Enjoy!
