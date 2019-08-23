# CRM remote Service & SWING Client using SQL Server.

Customer Order Management system, exposes CRM endpoints using SPRING MVC & SPRING-HESSIAN exporter.
This CRM Service is Multi company and once can define multiple Customer, Products, Sales Person management for a company. There is also a  reporting service for managing and printing online transactions like orders and bills, Quaterly and yearly sales and consolidations.

# Technical.
To successfully build the service for local access, one needs to download and build the required repos as mentioned below. There is a centralised parent POM which can download the shared libs. 

1. CustParent : Contains common dependencies including frameworks required for building the Service and Swing Client. [CRM Parent](https://github.com/ISingh2015/CustParent) 
2. CustBuild. Module to build required submodules required in the CRM system. [CRM Build](https://github.com/ISingh2015/CustBuild) 
3. Cust App Client.  Module for creating Web service Stubs.[CRM Stubs](https://github.com/ISingh2015/CustAppClient) 
4. CustWeb - Web Module for downloading CrM Windows Setup File.[CRM Stubs](https://github.com/ISingh2015/CustWeb) 

Once started the SWING clients can connect and consume the service endpoints for loggin in and accessing remote CRM data.
Links pointing to individual repos 
1. [CRM remote Service](https://github.com/ISingh2015/CustRenoteService) 
2. [Java Thin client SWING](https://github.com/ISingh2015/CustLoginManager)

