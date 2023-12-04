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

Bu teknoloji ve framework'ler, bir Java Spring Boot mikroservis projesi geliştirmek için geniş bir yelpazeyi kapsar.
