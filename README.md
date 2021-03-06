# CRM remote Service & SWING Client using SQL Server.

Customer Order Management system, exposes CRM endpoints using SPRING MVC & SPRING-HESSIAN exporter.
This CRM Service is Multi company and once can define multiple Customer, Products, Sales Person management for a company. There is also a  reporting service for managing and printing online transactions like orders and bills, Quaterly and yearly sales and consolidations.

# Instruction on how to build Locally on Windows.
One needs to clone the required CRM repos as mentioned below to successfully build and run the CRM service on a local windows o/s.

1. CustParent : Contains common dependencies including frameworks required for building the Service and Swing Client. [CRM Parent](https://github.com/ISingh2015/CustParent) 
2. CustBuild. Module to build required submodules required in the CRM system. [CRM Modules Build](https://github.com/ISingh2015/CustBuild) 
3. Cust App Client.  Module for creating Web service Stubs.[CRM Client Stubs](https://github.com/ISingh2015/CustAppClient) 
4. CustWeb - Web Module for downloading CrM Windows Setup File.[CRM SWING Download](https://github.com/ISingh2015/CustWeb) 

Customise the database connection properties in 4 above (Property file under resource folder), then build the CUSTWEB module to create the WAR File.
Run the CUSTWEB module in a tomcat container after deployment, to start the CRM Service.

Once CRM Web is up and running, Windows demo clients can register a single admin user license for downloading the CRM Swing client setup file, this is an executable setup with step by step instructions on setting up the windows client. Once setup of Swing Client is complete, double click the CRM Icon on the Desktop to run the application and login to the remote CRM Service using your credentials while registering. 

Admin user can create users with different ROLES for managing data entry and reporting. The admin user registration is valid for a periiod of a month  / year depending on the registration for exploring the CRM features like Customer, Sales Person, Orders, Billing, recording payment transactions & reporting. 

For queries or FAQ's or Enterprise CRM unlimited / yearly access or multi user access, please email IT [Inderjit Singh S](mailto:inderjitsanhotra@gmail.com?subject=[GitHub]%20Source%20Enterprise%20Access)

Links pointing to individual repos 
1. [CRM remote Service](https://github.com/ISingh2015/CustRenoteService) 
2. [Java Thin client SWING](https://github.com/ISingh2015/CustLoginManager)

