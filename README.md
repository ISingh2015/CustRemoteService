# CRM Service for SWING Client using SQL Server.

Customer Order Management remote service, exposes CRM endpoints using SPRING-HESSIAN exporter.
The CRM Service is Multi company having multiple Customer, Products, Sales Person management including some report Reportsm, it is built using Spring Frame 4 and uses the Spring-hibernateDAO & HESSIAN exporter. 

One needs to download and build maven Dependency as mentioned below for starting up the web service. Once  started the SWING clients can connect and consume the service endpoints for loggin in and accessing remote CRM data.

1. CustBuild. Module to build required submodules required in the CRM system.
2. Cust Service. Module containing the DAO and Service Layers implementation used in the web application (below 5)
3. Cust App Client.  Module creating the Client Web service Interface and Client VO.
4. Customer Login Manager. Module with centralised access to the remote service URL's.
5. CustWeb - Web Module for creating user access and allwoing downloadi of the SWING client

Links pointing to individual repos 
1. Java Thin client SWING: https://github.com/ISingh2015/CustLoginModule

