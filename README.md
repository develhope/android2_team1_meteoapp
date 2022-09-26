# Forecast App project
show the meteo info for a specific location

We will follow [figma](https://www.figma.com/file/GPQcB8SKX8XqOfWA3T0o0r/UX%26Devs?node-id=6%3A2) design.

### This app has 4 screens:
 - The main screen is a summary of weather for a specific location for today and the next 5 days 
 - The second screen is about today weather and is reachable through the second icon of navigation bar
 - The third screen is about tomorrow weather and is reachable through the third icon of navigation bar
 - The fourth screen is about a day weather and is reachable through clicking on a day on main screen

### Separation 
- A first step could be create all the stuff to make the ui/ux part work with mocked info.
This means to create all the screens, all data classes needed to fill ui and an object that contains all the data related to the ui.
- A second step is to use info from network using dto and connect the objects with ui related object.
- A third step is to save and retrieve informations from local storage and use them as place holder.
- A fourth step could be to create a screen with all the supported location and have weather for the location selected by the user.

DeadLines
A deadlines is a point where a live demo of the project will be shown to some guests.
Each deadline is not fixed but is better to be prepared to show the project
Here a simple list with the general achivements for the deadline
(P.s. in each deadline the most important bug fixes should be done)

- 17/10 Ui Layer (Acticy, fragment ui data object etcetc)
- 7/11 Network calls and DTO
- 5/12 Architectural Pattern 
- 19/12 Persistence
- 9/01 TBD
