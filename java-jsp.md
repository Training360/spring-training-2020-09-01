class: inverse, center, middle

# Java alapú webfejlesztés servlet és JSP technológiával

---

class: inverse, center, middle

# Bevezetés

---

## Referenciák


---

class: inverse, center, middle

# HTTP és a servletek

---

## HTTP(S) protokoll

* 1999-ben kiadott RFC 2616 definiálja a HTTP/1.1-et (W3C szervezet)
* 2015-ben leváltott a HTTP/2.0-ás verzió, amit az RFC 7540 definiál
* Kliens-szerver kommunikáció
* Kliens tipikusan böngésző
* Kérés-válasz alapú protokoll
* Szöveges
* Fejléc, törzs
* Állapotmentes
* (S): secure - SSL alapú titkosítás
* Eszközök: Böngésző Fejlesztői eszköztár/Fejlesztői eszközök (`F12`)

---

## HTTP kérés

```
Accept: text/html,application/xhtml+xm…plication/xml;q=0.9,*/*;q=0.8
Accept-Encoding: gzip, deflate
Accept-Language: hu-HU,hu;q=0.8,en-US;q=0.5,en;q=0.3
Cache-Control: max-age=0
Connection: keep-alive
Cookie: _ga=GA1.2.1967894445.149906973…yis3b41advsb3cwc3b3rk; _gat=1
Host: training360.com
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; …) Gecko/20100101 Firefox/57.0

GET http://training360.com/ HTTP/1.1
```

---

## HTTP válasz

```
HTTP/1.1 200 OK
Cache-Control: no-cache
Pragma: no-cache
Content-Type: text/html; charset=utf-8
Content-Encoding: gzip
Server: Kestrel
Set-Cookie: Training360.Session=CfDJ8...s5; path=/; secure; samesite=lax; httponly
Date: Fri, 09 Aug 2019 11:45:51 GMT

<!DOCTYPE html>
<html lang="hu">
<head>
  <!-- ... -->
</head>
<body>
  <!-- ... -->
</body>
</html>
```

---

## Hivatkozott erőforrások

* CSS
* JavaScript
* Képek (tipikusan gif, jpg, svg formátumban)
	* Formátumok közötti különbségek

---

## Servlet technológia

* Kérés-válasz alapú kommunikációra kidolgozott Java szabvány
* Tipikusan a HTTP protokoll kezelésére
* Szükség van egy webkonténerre
	* Legelterjedtebbek: Tomcat, Jetty, stb.
* Java Servlet 4.0, 3.0 verziónál nagy változás (`web.xml` nem szükséges)
* Direktben ritkán használjuk, erre épülnek a keretrendszerek
	
---

## Függőségek

* `<packaging>war</packaging>`

```xml
<dependency>
	<groupId>javax.servlet</groupId>
	<artifactId>javax.servlet-api</artifactId>
	<version>4.0.1</version>
	<scope>provided</scope>
</dependency>
```

---

## Pluginek

```xml
<finalName>locations</finalName>
<plugins>
	<plugin>
		<groupId>org.apache.maven.plugins</groupId>
		<artifactId>maven-war-plugin</artifactId>
		<version>3.3.1</version>
		<configuration>
			<failOnMissingWebXml>false</failOnMissingWebXml>
		</configuration>
	</plugin>
	
	<plugin>
		<groupId>org.eclipse.jetty</groupId>
		<artifactId>jetty-maven-plugin</artifactId>
		<version>9.4.31.v20200723</version>
	</plugin>
</plugins>
```
	
* `mvn package` hatására létrejön a `target/locations.war` állomány
* `mvn jetty:run` futtatja a konténert, és benne az alkalmazást

---

## Első servlet

```java
@WebServlet("/hello.html")
public class HelloServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		try (
			PrintWriter writer = resp.getWriter();
		) {
			writer.print("<html><body><p>Hello Servlet World!</p></body></html>");
		}
	}

}
```

---

## WAR állomány

* `locations.war`

```
WEB-INF
	classes
		locations
			HelloServlet.class
```

---

class: inverse, center, middle

# Servlet életciklusa

---

## Életciklus

* A webkonténer saját hatáskörben dönt, hány példányt hoz létre
* Egyszer meghívja az `init()` metódust
* Kérésenként, külön szálon meghívja a `service()` metódust
	* HTTP metódus alapján hívja a `doGet()`, `doPost()` metódusokat
* Szabályos leállításkor egyszer meghívja a `destroy()` metódust

---

## Alapértelmezett implementáció

```java
@WebServlet("/hello.html")
public class HelloServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		try (
			PrintWriter writer = resp.getWriter();
		) {
			writer.print("<html><body><p>Hello Servlet World!</p></body></html>");
		}
	}

}
```

---

class: inverse, center, middle

# Statikus állományok

---

## Statikus állományok

* `src/main/webapp` könyvtárba, pl. `index.html`
* A war állomány gyökerébe kerülnek
* Akár könyvtárakat is

---

class: inverse, center, middle

# Model 2

---

## Model 2

* MVC (Model-View-Controller)
	* Model: JavaBeans
	* View: JSP oldal
	* Controller: Servlet
* Kérés-válasz
	* Controller fogadja a kérést
	* Előállítja/lekérdezi a modelt
	* Request scope-ú attribútumként továbbadja a view-nak
	* Továbbítja a kérést a view-nak (forward)
	* View lerendereli a választ (, miközben kiveszi a request scope-ú attribútumot)

---

## Controller

```java
@WebServlet("/locations.html")
public class LocationsServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.setAttribute("locations", Locations.of("Budapest", "Pécs", "Debrecen));		
		req.getRequestDispatcher("/WEB-INF/jsp/locations.jsp").forward(req, resp);
	}
}
```

---

## JSP technológia

* Főleg statikus elemek, kevesebb dinamikus tartalommal
* Servletté fordul át
* EL: expression language
* Hivatkozás a különböző scope-ú változókra, kifejezések, függvények hívása

---

## View

* `/WEB-INF/jsp/locations.jsp`

```xml
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>
    <h1>Locations</h1>
    
	<%-- List objektum request attribútumból --%>
	
	<p>${locations}</p>
</body>
</html>
```

---

class: inverse, center, middle

# Architektúra

---

## Model

* Java POJO, DTO (Data Transfer Object)

```java
public class Location {

	private Long id;
	
	private String name;
	
	// További attribútumok
	
	// Konstruktorok
	
	// Getter és setter metódusok
}
```

---

## Service

* A servlet csak delegálja a kérést az üzleti logikát tartalmazó service felé

```java
public class LocationsService {

	public List<Location> getLocations() {
		// ...
	}
}
```

---

## Servlet hivatkozás

```java
@WebServlet("/locations.html")
public class LocationsServlet extends HttpServlet {

	private LocationsService locationsService = new LocationsService();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {

		List<Location> locations = locationService.getLocations();
		req.setAttribute("locations", locations);
		
		req.getRequestDispatcher("/WEB-INF/jsp/locations.jsp").forward(req, resp);
	}
	
}
```

---

## Függőség

```xml
<dependency>
	<groupId>javax.servlet</groupId>
	<artifactId>jstl</artifactId>
	<version>1.2</version>
	<scope>provided</scope>
</dependency>
```

---

## Taglib

* Scriptlet: Java kód
* Taglib újrafelhasználható tageket tartalmaz, mely mögött Java kód áll
* Standart tagek: JSP Standard Tag Library
	* Core: változók kezelése, vezérlés, URL kezelés
	* XML: xml kezelés, transzformáció
	* I18N: regionális beállítások és üzenetek, szám és dátum formázás
	* SQL: SQL utasítások
	* Functions: stringek és collectionök kezelése
* NE írjunk scriptleteket, csak taglibeket használjunk

---

## JSTL

```xml
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<table>
	<c:forEach items="${locations}" var="location">
		<tr>
			<td>
				${location.id}
			</td>
			<td>
				${location.name}
			</td>
			<td>
				${location.lat}, ${location.lon}
			</td>
			</tr>
	</c:forEach>
</table>
```

---

class: inverse, center, middle

# URL paraméterek

---

## URL paraméter használata

* `location.html?id=1`

```java
@WebServlet("/location.html")
public class LocationServlet extends HttpServlet {
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		long id = Long.parseLong(req.getParameter("id"));
		
		Location location = new LocationService().findById(id);
		
		req.setAttribute("location", location);
		
		req.getRequestDispatcher("/WEB-INF/jsp/location.jsp").forward(req, resp);
	}

}

```

---

class: inverse, center, middle

# Módosítás - POST metódus

---

## Folyamat

* Űrlap megjelenítése
	* GET metódusú kérés a servletnek
* Űrlap submit
	* POST metódusú kérés a servletnek
* Átirányítás az eredmény oldalra (redirect)
	* Redirect after post tervezési minta
	
---

## Űrlap - JSP

```xml
<form method="post">
	<input name="name" />
	<input name="lat" />
	<input name="lon" />
	<input type="submit" value="Create new location" />
</form>
```

---

## Űrlap megjelenítése servlet - GET

```java
@WebServlet("new-location.html")
public class NewLocationServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/jsp/new-location.jsp").forward(req, resp);
	}

}

```

---

## Űrlap adatok fogadása - POST

```java
@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
	String name = req.getParameter("name");
	double lat = Double.parseDouble(req.getParameter("lat"));
	double lon = Double.parseDouble(req.getParameter("lon"));

	new LocationService().addLocation(new Location(name, lat, lon));
	
	resp.sendRedirect("locations.html");
}
```

---

class: inverse, center, middle

# Adat átvitele több request között - session

---

## Flash scope

* Klasszikusan a redirect after post esetén használatos
* POST-nál előáll az adat
* GET-nél kell megjeleníteni
* Kérések között session használata
* Flash scope: Servlet API-ban nem értelmezett
* Sessionbe kerül, majd onnan eltávolításra

---

## POST

```java
protected void doPost(HttpServletRequest req, HttpServletResponse resp) 
		throws ServletException, IOException {
	
	// ...
	
	req.getSession().setAttribute("message", "Location has created: " + name);
	
	// ...
}
```

---

## GET

* Session attribútumból átteszi request attribútumba

```java
@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
			throws ServletException, IOException {
		
		// ...
		
		String message = (String) req.getSession().getAttribute("message");
		if (message != null) {
			req.setAttribute("message", message);
			req.getSession().removeAttribute("message");
		}

		// ...
	}
```

---

## Megjelenítés JSP oldalon

```xml
<c:if test="${not empty message}">
	<p>${message}</p>
</c:if>
```

---

class: inverse, center, middle

# Filterek

---

## CharacterEncodingFilter

```java
@WebFilter(urlPatterns = "/*")
public class CharacterEncodingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
        System.out.println("Run filter");
        request.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
```

---

class: inverse, center, middle

# Header és Cookie kezelés

---

## Header

```java
System.out.println(req.getHeader("User-Agent"));

resp.setHeader("Server-Time", LocalDateTime.now().toString());
```

---

## Cookie

```java
Optional<Cookie> lang = Arrays.stream(req.getCookies())
	.filter(c -> c.getName().equals("Lang")).findAny();
if (lang.isPresent()) {
	System.out.println(lang.get().getValue());
}
else {
	resp.addCookie(new Cookie("Lang", "hu_HU"));
}
```

---

class: inverse, center, middle

# ServletContext

---

## Listener

* Application scope

```java
@WebListener
public class ServiceInitContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        sce.getServletContext().setAttribute("locationService", new LocationService());
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
}
```

---

## Servlet

```java
@WebServlet("/locations.html")
public class LocationsServlet extends HttpServlet {

	private LocationService locationService;

	@Override
	public void init() throws ServletException {
		locationService = (LocationService) getServletContext().getAttribute("locationService");
	}
	
	// ...
}
```

---

class: inverse, center, middle

# Hibakezelés

---

## web.xml

```xml
<web-app version="4.0">
    <error-page>
        <error-code>404</error-code>
        <location>/404.html</location>
    </error-page>

    <error-page>
        <exception-type>java.lang.IllegalArgumentException</exception-type>
        <location>/404.html</location>
    </error-page>
</web-app>
```

---

class: inverse, center, middle

# Laptöredékek újrafelhasználhatósága

---

## JSP include

```xml
<jsp:include page="header.jsp" />
```

