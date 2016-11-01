# Authenticator
A library for generating and verifying one-time expirable passwords.

##Download

Download [the latest JAR][1].

##Modules

###Authenticator module
The core library can be found in the [authenticator module](authenticator).

####Usage
The Authenticator has a very slim API, you can generate and validate passwords like so:

```Java
Authenticator authenticator = new Authenticator();
int generatedPassword = authenticator.generatePassword("user-id");
boolean valid = authenticator.isPasswordValid("user-id", password);
```

A password is valid for 30 seconds from its creation.

###Examples module
In the [examples module](examples) you can find an interactive terminal-based example based for the Authenticator library.

###Scripts
If you want to build the whole project, run all the tests and the code analysis tools then run the file:

`scripts/build.sh`

For starting the example straight away then run the file:

`scripts/run_example.sh`

##License
This application itself is released under **MIT** license, see [LICENSE](./LICENSE).

All 3rd party open sourced libs distributed with this application are still under their own license.

 [1]: https://github.com/AkiKanellis/authenticator/releases/download/v1.0/authenticator-1.0.jar