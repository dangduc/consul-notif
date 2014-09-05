consul-notif
============

Send email notifications when Consul node checks change state

How Does It Work?
------------
The notifier periodically polls a Consul server over HTTP. An in-memory cache is used to store the last state of all node health checks. When a poll detects a health state change, an email is sent via SMTP. The notifier is designed to parse the JSON response of the URI, `v1/internal/ui/nodes?dc=<your center>`.

How Do I Use It?
------------

From the command-line, `cd` into the repository directory and invoke:

	gradle build

If the build succeeds, the jar `consul-notif-1.0.jar` should be in the sub-directory `build/libs`. 

The consul-notif jar can be run anywhere Java 7 is installed, because its dependencies are included. 

Before running consul-notif, make sure to set up a configuration file named `consul-notif.json`. Here's an example:

	{
	  "sleepDurationMillis": 1000,
	  "consulResourceUrl": "http://demo.consul.io/v1/internal/ui/nodes?dc=dc1",
	  "smtpServer": "smtp.gmail.com",
	  "smtpUser": "consuluser@gmail.com",
	  "smtpPass": "pass456",
	  "notifSenderEmail": "consuluser@gmail.com",
	  "notifRecipientEmail": "joe@gmail.com"
	}

consul-notif looks for this configuration file in the same directory the jar is invoked.

consul-notif is run by invoking:

	java -jar consul-notif-1.0.jar

consul-notif writes its logs to the file consul-notif.log in the same directory the jar is invoked.

Compatiblity
------------
The notifier is compatible with Consul (http://www.consul.io/) version 0.3.1.
