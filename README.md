# EAP XP OpenApi test

Demonstrates problem of using utility library DTOs with EAP 7.5.4/XP 4.0.0 as reported [to Red Hat](https://access.redhat.com/support/cases/#/case/03253183)

The ear application contains a utility jar (dto) used by both EJB and WEB projects.

The web application has a resource returning both a DTO from the utility jar, and one returning a DTO from the web project.

## Test

Build the application:

	./gradlew ear
	
Copy the application to EAP deploy folder:

	cp build/libs/eap-openapi.ear (eap74 root)/standalone/deployments/eap-openapi.ear
	
Start the server

	(eap74 root)/bin/standalone.sh
	
	
Test that the two DTOs can be returned:

	$ curl localhost:8080/web/services/web-local
	{"webFoo":"a web DTO"}
	
	$ curl localhost:8080/web/services/utility
	{"utilityFoo":"a utility DTO"}

Now get the OpenApi document (and notice that the utility DTO is replaced with a plain object):

```console
$ curl localhost:8080/openapi
---
openapi: 3.0.3
info:
  title: web.war
  version: "1.0"
servers:
- url: /web
paths:
  /services/utility:
    get:
      responses:
        "200":
          description: OK
          content:
            application/json:
              schema:
                type: object
  /services/web-local:
    get:
      responses:
        "200":
          description: OK
          content:
            application/json:
              schema:
                $ref: '#/components/schemas/WebDto'
components:
  schemas:
    WebDto:
      type: object
      properties:
        webFoo:
          type: string
```
