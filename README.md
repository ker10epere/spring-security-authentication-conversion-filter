# INFO

Simulating conversion of Authentication to a different type using filter. `BasicToJwtAuthenticationFilter` is the filter
created for simulation

Simulating conversion of `UsernamePasswordAuthenticationToken` to `JwtAuthenticationToken`, then enriching the new
AuthenticationToken with real JWT value from `X-TOKEN` header.

Added test to simulate testing of actual `SecurityConfig`.

Sample Request

```http request
GET /pet HTTP/1.1
Host: localhost:8080
X-TOKEN: eyJ1c2VySWQiOiJhYmNkMTIzIiwiZXhwaXJ5IjoxNjQ2NjM1NjExMzAxfQ
Authorization: Basic a2VyOmtlcg==
Cookie: JSESSIONID=5E3C2BA78AE205697290CDA2F31503D0
```