# startertemplate
Starter archetype template

settings.xml example:

<settings>
  <servers>
    <server>
      <id>docker.io</id>
      <username>man4j</username>
      <password>password</password>
    </server>
  </servers>

  <profiles>
    <profile>
      <id>default</id>
      <activation>
        <activeByDefault>true</activeByDefault>
      </activation>
      <properties>
        <docker.db.password>password</docker.db.password>
      </properties>
   </profile>
  </profiles>
</settings>
