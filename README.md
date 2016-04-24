# Spring Boot with Spring Security

```java
    @Bean
    public static WebSecurityConfig webSecurityConfig() {
        return new WebSecurityConfig();
    }
```

```java
    public static void main(String[] args) {
        SpringApplication.run(new Object[] { Application.class, WebSecurityConfig.class }, args);
    }
```
    