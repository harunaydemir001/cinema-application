## Proje Hakkında

Bu mikroservis projesi, modern teknolojileri kullanarak geliştirilmiş ve ölçeklenebilir bir yapı sunmayı amaçlamaktadır. Ayrıca, RESTful web servisleri kullanarak veri işleme ve depolama sağlamaktadır.

### Teknoloji ve Framework'ler

#### Java 21 ve İlgili Araçlar

- **Java 21**: Java'nın son sürümü.
- **Maven**: Proje yönetimi ve bağımlılık yönetimi için kullanılır.
- **Spring Boot 3**: Microservices tabanlı uygulamalar geliştirmek için kullanılan Spring Framework'ün bir parçası.

#### Geliştirme Araçları

- **Lombok**: Java projelerinde boilerplate kodunu azaltmak için kullanılır.
- **MapStruct**: Java bean mapper aracı, nesne dönüşümlerini kolaylaştırır.
- **Validation**: Veri doğrulama için Java Bean Validation API.

#### Spring ve Microservices

- **Spring Cloud**
  - **Eureka Client**: Servis kayıt ve bulma için kullanılır.
  - **OpenFeign**: Declarative REST client, servisler arası iletişim sağlar.
  - **Config Server**: Uygulama konfigürasyonlarını merkezi bir yerden yönetir.
  - **Gateway Server**: API gateway olarak kullanılır.

#### Veritabanı ve Veri Depolama

- **JPA (Java Persistence API)**: Java uygulamaları için nesne ilişkisel eşleme standardı.
- **Hibernate**: JPA'nın bir implementasyonu, veritabanı işlemlerini kolaylaştırır.
- **MongoDB**: NoSQL veritabanı, belge tabanlı veri depolama için kullanılır.
- **MySQL**: İlişkisel veritabanı yönetim sistemi.

#### Mesajlaşma ve Asenkron İletişim

- **Kafka**: Dağıtılmış ve dayanıklı mesajlaşma sistemi.

#### Cache ve Durability

- **Redis**: Anahtar-değer tabanlı önbellek ve veritabanı.

#### Microservices Güvenilirliği

- **Resilience4j**: Hata toleransı, hata işleme ve hata çözme için kullanılır. Proje de circuit-breaker, rate-limiter, time-limiter, retry kullanılmıştır.
- **AOP (Aspect-Oriented Programming)**: Birleşim (cross-cutting) konuları ele almak için kullanılır.

#### Test ve Güçlendirme

- **JUnit 5 ve Mockito**: Java uygulamalarını test etmek için kullanılır.

#### Diğerleri

- **Actuator**: Uygulamanın çalışma zamanı bilgilerini sağlar.
- **OpenAPI 3**: API belgelendirme ve tasarım için kullanılır.
- **Spring Cloud Gateway**: API trafiğini yönetmek için kullanılır.
- **Specification**: Belirli bir veritabanı sorgusu için kriterleri ifade etmek için kullanılır.
- **Circuit Breaker**: FeignClient için fallback oluşturulmuştur. Method levelde fallback metodu oluşturulmuştur.

### Tasarım Desenleri (Design Patterns)

- **Factory Design Pattern**: Nesne yaratma sorumluluğunu taşıyan bir sınıfın yaratılmasını sağlar.

- **Singleton Design Pattern**: Bir sınıfın yalnızca bir örneğinin oluşturulmasını ve genel bir erişim noktası sağlamayı amaçlar.

- **Builder Design Pattern**: Karmaşık bir nesnenin adım adım oluşturma sürecini kontrol etmeyi amaçlar.

- **Model-View-Controller (MVC) Design Pattern**: Kullanıcı arayüzü ile uygulama mantığı ve veri arasındaki ayrımı sağlar.

Bu teknoloji, frameworkler ve tasarım desenleri, bir Java Spring Boot mikroservis projesi geliştirmek için geniş bir yelpazeyi kapsar.

## Bağımlılıklar

- [Spring Boot 3](https://spring.io/projects/spring-boot)
- [Apache Kafka](https://kafka.apache.org/)
- [Redis](https://redis.io/)
- [Maven](https://maven.apache.org/)
- [Hibernate](https://hibernate.org/)
- [Spring Cloud Hizmetleri](https://spring.io/projects/spring-cloud)
- [MongoDB](https://www.mongodb.com/)
- [OpenAPI 3](https://swagger.io/specification/)
- [JUnit 5 ve Mockito](https://junit.org/junit5/)
- [Resilient4j](https://resilience4j.readme.io/)
- [MySQL](https://www.mysql.com/)
- [Lombok](https://projectlombok.org/)
- [Java 21](https://spring.io/blog/2023/09/20/hello-java-21)
