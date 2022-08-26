Projeto Blog Spring Boot e Thymeleaf com deploy na AWS

1) Criar uma AWS access keys. Segue link de como criar.
https://docs.aws.amazon.com/general/latest/gr/aws-sec-cred-types.html#access-keys-and-secret-access-keys

2) Instalar AWS CLI. Segue link de como instalar.
https://docs.aws.amazon.com/cli/latest/userguide/getting-started-install.html

3) Instalar o eb. Segue link de como instalar.
https://github.com/aws/aws-elastic-beanstalk-cli-setup

4) Iniciar um app na aws: eb init

5) Criar um RDS Postgres na aws: eb create --scale 1 -db -db.engine postgres -db.i db.t3.micro
Observação: Verificar qual instancia está disponível na AWS.

6) Criar um arquivo application-beanstalk.properties:
spring.datasource.url=jdbc:postgresql://${rds.hostname}:${rds.port}/${rds.db.name}
spring.datasource.username=${rds.username}
spring.datasource.password=${rds.password}

7) Criar um Profile no arquivo pom.xml:

<profiles>
    <profile>
        <id>beanstalk</id>
        <build>
            <finalName>${project.name}-eb</finalName>
            <plugins>
                <plugin>
                    <groupId>org.springframework.boot</groupId>
                    <artifactId>spring-boot-maven-plugin</artifactId>
                </plugin>
                <plugin>
                    <groupId>org.apache.maven.plugins</groupId>
                    <artifactId>maven-compiler-plugin</artifactId>
                    <configuration>
                        <excludes>
                            <exclude>**/cloud/config/*.java</exclude>
                        </excludes>
                    </configuration>
                </plugin>
            </plugins>
        </build>
    </profile>
</profiles>

8) Gerar o arquivo .jar com o seguinte comando: mvn clean package spring-boot:repackage

9) Adicionar o nome do arquivo gerado .jar no arquivo de configuração elasticbeanstalk/config.yml: 
deploy:
  artifact: target/nome-arquivo-gerado.jar

10) Fazer o deploy: eb deploy

11) Criar 2 variáveis de ambiente:
eb setenv SPRING_PROFILES_ACTIVE=beanstalk,mysql
eb setenv SERVER_PORT=5000

12) Conferir o status na interface aws ou no console: eb status# codeblog
