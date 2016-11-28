def list = new XmlSlurper().parseText(xml);

String name = list.technology.name;

if (name.trim().length() > 10) throw new Exception("Invalid name!");

return name;