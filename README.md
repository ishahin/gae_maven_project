gae_maven_project
=================

GAE Maven Spring Objectify

Este projeto foi desenvolvido para resolver o seguinte problema: como criar uma aplicação
que funcione corretamente usando Maven, GAE, Spring and Objectify? Apesar dos inúmeros tutoriais
encontrados no Google, poucos funcionavam e os que funcionavam demoravam muito para realizar o
build da aplicação.

Os membros da equipe precisam usar o poder do Maven, mas não podiam ficar esperando até cerca de
três minutos, dependendo da máquina, para executar o projeto.

Este modelo de aplicação une o poder do maven, mas permite que o projeto seja executado usando
o próprio plugin do Google, o que reduz o tempo para execução do projeto e consequentemente
aumenta a produtividade da equipe.

How to Build
============

EasyTeam

- Anderson Soares	
- Cleiton Moura
- Matheus Campanha
- Pedro Almir
- Rogério Figueredo
- Werney Ayala


1. This project was developed in the Eclipse IDE, then you need to get the Eclipse IDE.
   Eclipse Juno: www.eclipse.org/juno

2. You need to download a svn plugin for eclipse, I suggest Subversive.
   Subversive: http://www.eclipse.org/subversive/
   
3. You need to download a Maven plugin for eclipse.
   Maven: http://m2eclipse.sonatype.org/sites/m2e
   
4. You need to download a Google plugin for eclipse.
   Google Plugin: https://developers.google.com/eclipse/docs/install-eclipse-4.2
   
5. You need to download AppEngine SDK 1.7.4. After downloading unzip the SDK preferably in C:/appengine-java-sdk-1.7.4

6. Phew ... Now eclipse is ready to receive the project. xD
	
7. Check the variable <gae.home> in pom.xml to ensure that it's pointing to the location where you unzipped the SDK.

8. Execute "mvn:clean compile" to update lib folder.
   
9. From this point the project is ready for use. To build and run the project just click the right mouse 
button on the project, go to the menu Run As, then select the Web Application.

10. If all previous steps were performed correctly the project will be builded and you can access it at 
the URL http://localhost:8888/
    
11. Easy huh? Congratulations ... xD


Tips
====
	
Errors
======

1. If you are struggling with the following kind of error: java.lang.VerifyError: Expecting a stackmap frame at branch target 6 bla bla bla...
	1.1 A simple solution is to add the following default JVM argument -XX:-UseSplitVerifier
	1.2 If you are doing it in eclipse, like i was, then goto Window -> Preferences -> Installed JREs -> and add the default parameter.
	1.3 What does this setting do? Oracle just mentions (http://www.oracle.com/technetwork/java/javase/tech/vmoptions-jsp-140102.html) that it a new type checker with StackMapTable attributes. 
	
Huh! Enjoy! It's Oracle now.
