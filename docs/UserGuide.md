---
layout: page
title: User Guide
---

Bivago is a **desktop contact management app** meant for tour guides. Along with basic contact management, it also helps
you **efficiently look up contacts** associated with the different tour packages that you offer. The app supports
different types of contacts including **people, F&B establishments, accommodations and attractions**. Through
consolidating your contacts and tours in a simple **Command Line Interface (CLI)** application, Bivago helps you plan
and execute better and smoother tours for your clients.

* Table of Contents
{:toc}

---

## How it serves you

Bivago provides two main components:
* Contact Management
* Tour Management

### Contact Management
Through Contact Management, you can **store different types of contacts** you work with. You can store contacts for
people (e.g. drivers, shop owners), F&B establishments (e.g. street food vendors, restaurants), attractions (e.g.
museums, amusement parks) and accommodation (e.g. hotels, hostels). For each type of contact, they come with additional
 information relevant to that type such as Halal status, operating hours, and stars. Tags are also available for you
 to store any additional information.

### Tour Management
Through **Tour Management**, you can **store different tour packages** you offer and assign contacts with those tours.
When planning to conduct a specific tour, you can see the assigned contacts at a glance and with the relevant
information from the contacts, you can make informed decisions during tour planning. You can also benefit from tour
management while conducting the tour as you can quickly view contact details on the day itself and contact them.

## Quick start

1. Ensure you have Java `17` or above installed in your Computer.
<details>
<summary>How to install?</summary>

<b>Windows users:</b> Follow the installation instructions
<a href=https://se-education.org/guides/tutorials/javaInstallationWindows.html>here</a>.<br>
<b>Mac users:</b> Follow the installation instructions
<a href=https://se-education.org/guides/tutorials/javaInstallationMac.html>here</a>.<br>
<b>Mac users:</b> Follow the installation instructions
<a href=https://se-education.org/guides/tutorials/javaInstallationMac.html>here</a>.<br>

</details><br>

2. Download the latest `.jar` file from [here](https://github.com/AY2526S2-CS2103T-W08-1/tp/releases).

3. Copy the file to the folder you want to use as the _home folder_ for Bivago.

4. Open a command terminal.

6. Type `cd FILEPATH` to navigate to the folder you put the jar file in.

7. Type `java -jar Bivago.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

8. Type a command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will
   display the program usage instructions.<br>
   Some example commands you can try:

* `help` : Displays the help message.

* `list` : Lists all contacts.

* `add type/person n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01` : Adds a contact
  named `John Doe` to the contact list.

* `tour-add n/City Walking Tour` : Adds a tour named `City Walking Tour` to the tour list.

* `delete 3` : Deletes the third contact shown in the current contact list.

* `exit` : Exits the app.

9. Refer to the [Features](#features) below for details of each command.

---

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

* Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

* Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) or
 parameters not specific to the contact type will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines
  as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

---

## General

### Viewing help : `help`

Shows a message explaining how to use the application.

Format: `help`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

Bivago data are saved in the hard disk automatically after any command that changes the data. There is no need to save
manually.

### Editing the data file

Bivago data are saved automatically as a JSON file `[JAR file location]/data/bivago-data.json`.

<div markdown="span" class="alert alert-warning">:exclamation: **Caution:**
You are encouraged to make changes to the data using the commands provided by the app instead of manually editing the
 data file. If you make changes to the data file making its format invalid, Bivago will discard all data and start with
an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the Bivago to behave in unexpected ways (e.g., if a value entered is outside of
 the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</div>

---

## Contact Management

### Adding a contact: `add`

Adds a contact to the contact list.

Format:
`add type/TYPE n/NAME p/PHONE e/EMAIL a/ADDRESS [h/HALAL_STATUS] [o/OPENING_HOUR] [c/CLOSING_HOUR] [s/STARS] [t/TAG]…​`

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A contact can have any number of tags (including 0)
</div>

Available types: `person`, `fnb`, `accomm`, `attraction`

**Type-specific Fields**:
* F&B contacts: `[h/HALAL_STATUS]`
* Attraction contacts: `[o/OPENING_HOUR] [c/CLOSING_HOUR]`
* Accommodation contacts: `[s/STARS]`

**Field Constraints**:
* Halal Status must be `true` or `false` (default: `false`)
* Opening Hours must be in `HH:mm` 24-hour format (default: `08:00`)
* Closing Hours must be in `HH:mm` 24-hour format (default: `22:00`)
* Stars must be a single digit from `1–5` (default: `3`)

<div markdown="span" class="alert alert-warning">:exclamation: **Important:**
Fields that are not applicable to the specified contact type will be ignored.
For example, `h/true` will not apply to `person` contacts.
</div>

Examples:
* `add type/person n/John Doe p/98765432 e/johnd@example.com a/311 Clementi Ave 2` : Adds a person contact named `John Doe` to the contact list.

* `add type/fnb n/Nasi Lemak Stall p/91234567 e/fnb@example.com a/Market Street h/true` : Adds an F&B contact named `Nasi Lemak Stall` with halal status set to `true`.

* `add type/attraction n/USS p/67891234 e/uss@example.com a/Sentosa o/09:00 c/21:00` : Adds an attraction named `USS` with operating hours from `09:00` to `21:00`.

* `add type/accommodation n/Hotel 81 p/61234567 e/hotel@example.com a/Geylang s/4` : Adds an accommodation named `Hotel 81` with a `4`-star rating.

### Listing all contacts : `list`

Shows a list of all contacts in the contact list.

Format: `list`


### Editing a contact : `edit`

Edits an existing contact in the contact list.

Format:
`edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [h/HALAL_STATUS] [o/OPENING_HOUR] [c/CLOSING_HOUR] [s/STARS] [t/TAG]…​`

* Edits the contact at the specified `INDEX`
* The index refers to the number shown in the displayed list
* The index **must be a positive integer** 1, 2, 3, …​
* At least one field must be provided
* Existing values will be overwritten
* When editing tags, existing tags are removed and replaced
* To remove all tags, use `t/` with no value

Examples:
* `edit 1 p/91234567 e/johndoe@example.com` : Edits the first contact by updating the phone number to `91234567` and email to `johndoe@example.com`.

* `edit 2 n/New Name t/` : Edits the second contact by updating the name to `New Name` and clearing all existing tags.

### Searching contacts by name: `find`

Finds contacts whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

* Case-insensitive (e.g. `john` matches `John`)
* Order does not matter
* Only names are searched
* Matches **full words only**
* Returns contacts matching at least one keyword (OR search)

Examples:
* `find John` : Finds and lists all contacts whose names contain `John`.

* `find alex david` : Finds and lists all contacts whose names contain either `alex` or `david`.

### Deleting a contact : `delete`

Deletes the specified contact from the contact list.

Format: `delete INDEX`

* Deletes the contact at the specified `INDEX`
* Index must be a positive integer

Examples:
* `delete 2` : Deletes the second contact shown in the current contact list.

* `find John` followed by `delete 1` : Finds contacts with names containing `John`, then deletes the first contact from the search results.

---

## Tour Management

### Adding a tour: `tour-add`

Adds a tour package to the tour list.

Format: `tour-add n/NAME`

Examples:
* `tour-add n/Le Royal Tour` : Adds a tour named `Le Royal Tour` to the tour list.

### Listing tours: `tour-list`

Shows all available tours in the tour list.

Format: `tour-list`

### Assigning a tour: `tour-assign`

Assigns a contact to a tour.

Format: `tour-assign CONTACT_INDEX tour/TOUR_INDEX`

* Both indices must be positive integers

Examples:
* `tour-assign 1 tour/2` : Assigns the first contact in the current contact list to the second tour in the current
tour list.

### Unassigning a tour: `tour-unassign`

Unassigns a contact from a tour.

Format: `tour-unassign CONTACT_INDEX tour/TOUR_INDEX`

* Both indices must be positive integers

Examples:
* `tour-unassign 3 tour/5` : Unassigns the third contact in the current contact list from the fifth tour in the current
  tour list.

### Viewing a tour: `tour-view`

Displays all contacts assigned to a specified tour.

Format: `tour-view INDEX`

Examples:
* `tour-view 1` : Displays all contacts assigned to the first tour in the current tour list.

### Searching tours by name: `tour-find`

Finds tours whose names contain any of the given keywords.

Format: `tour-find KEYWORD [MORE_KEYWORDS]`

* Case-insensitive (e.g. `foodie` matches `Foodie`)
* Order does not matter
* Only names are searched
* Matches **full words only**
* Returns tours matching at least one keyword (OR search)

Examples:
* `tour-find Foodie` : Finds and lists all tours whose names contain `Foodie`.

* `tour-find City Walking` : Finds and lists all tours whose names contain `City` or `Walking`.

### Deleting a tour: `tour-delete`

Deletes a tour package from the tour list.

Format: `tour-delete INDEX`

Examples:
* `tour-delete 1` : Deletes the first tour shown in the current tour list.

---

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains
the data of your previous Bivago home folder.

---

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only
   the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the
   application before running the application again.
2. **If you minimize the Help Window** and then run the `help` command (or use the `Help` menu, or the keyboard shortcut
   `F1`) again, the original Help Window will remain minimized, and no new Help Window will appear. The remedy is to
   manually restore the minimized Help Window.

---

## Command summary

### General

| Action | Format, Examples |
|--------|-----------------|
| **Help** | `help` |
| **Exit** | `exit` |

### Contact Management

| Action       | Format, Examples                                                                                                                                                                                                             |
|--------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add** | `add type/TYPE n/NAME p/PHONE e/EMAIL a/ADDRESS [h/HALAL_STATUS] [o/OPENING_HOUR] [c/CLOSING_HOUR] [s/STARS] [t/TAG]…​` <br> e.g., `add type/person n/John Doe p/98765432 e/john@example.com a/311 Clementi Ave 2 t/friend` |
| **Delete** | `delete INDEX` <br> e.g., `delete 3`                                                                                                                                                                                         |
| **Edit** | `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [h/HALAL_STATUS] [o/OPENING_HOUR] [c/CLOSING_HOUR] [s/STARS] [t/TAG]…​` <br> e.g., `edit 2 p/91234567 e/john_new@example.com`                                          |
| **Find** | `find KEYWORD [MORE_KEYWORDS]` <br> e.g., `find John Jane`                                                                                                                                                                   |
| **List** | `list`                                                                                                                                                                                                                       |


### Tour Management

| Action       | Format, Examples                                                                  |
|--------------|-----------------------------------------------------------------------------------|
| **Add**      | `tour-add n/NAME` <br> e.g., `tour-add n/Le Royal Tour`                           |
| **Delete**   | `tour-delete INDEX` <br> e.g., `tour-delete 2`                                    |
| **Assign**   | `tour-assign CONTACT_INDEX tour/TOUR_INDEX` <br> e.g., `tour-assign 1 tour/2`     |
| **Unassign** | `tour-unassign CONTACT_INDEX tour/TOUR_INDEX` <br> e.g., `tour-unassign 3 tour/5` |
| **View**     | `tour-view INDEX` <br> e.g., `tour-view 1`                                        |
| **Find** | `tour-find KEYWORD [MORE_KEYWORDS]` <br> e.g., `tour-find City Walking`           |
| **List**     | `tour-list`                                                                       |
