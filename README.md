This project is a sample Spring Boot app (Web MVC) that redirects all unresolved URL paths to the home page, as is quite common in single page applications.

To do this unconditionally, you need to sacrifice something from the default Spring Boot autoconfiguration because all paths have a handler by default in a vanilla Spring Boot application (you just get a 404 from unresolved resources). There are a couple of ways to achieve the functionality we are looking for, so this is just one of them with one set of choices and compromises. Another example can be found in this [tutorial](https://spring.io/guides/tutorials/spring-boot-oauth2/).

The basic plan is to switch the deafult resource handler (for static resources) to a non-default path, so this means we have

.application.properties
```
spring.mvc.static-path-pattern=/static/**
```

We also need to generate a signal when a resource is not found so we can handle it in a custom handler:

.application.properties
```
spring.mvc.throw-exception-if-no-handler-found=true
```

The custom handler is just a `@ControllerAdvice`:

```java
@Component
@ControllerAdvice
class Customizer {
	@ExceptionHandler(NoHandlerFoundException.class)
	public RedirectView notFound() {
		return new RedirectView("/");
	}
}
```

With that you can run the app and verify that the home page says "Hello", and so does a non-existent path, like `/missing` (via a redirect). Static assets in the normal location `src/main/resources/static` are still available, but need an explicit `/static/` prefix, hence the home page handler is written like this:

```java
@GetMapping("/")
public String home() {
	return "forward:/static/index.html";
}
```

(whereas a vanilla Spring Boot app wouldn't need this because "/" is automatically forwarded to "/index.html").
