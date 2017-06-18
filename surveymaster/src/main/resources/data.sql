insert into providers (ID, NAME) values('PR1', 'Provider 1');
insert into providers (ID, NAME) values('PR2', 'Provider 2');
insert into providers (ID, NAME) values('PR3', 'Provider 3');

insert into subjects (ID, DESCRIPTION) values(1, 'Subject 1');
insert into subjects (ID, DESCRIPTION) values(2, 'Subject 2');
insert into subjects (ID, DESCRIPTION) values(3, 'Subject 3');

insert into surveys (ID, PROVIDER_ID, SUBJECT_ID, AGEMAX, AGEMIN, COUNTRY, GENDER, INCOMECURRENCY, INCOMEMAX, INCOMEMIN, PRICE) values (11, 'PR1', 1, 45, 20, 'IT', 'M', 'EUR', 50000, 18000,5000);
insert into surveys (ID, PROVIDER_ID, SUBJECT_ID, AGEMAX, AGEMIN, COUNTRY, GENDER, INCOMECURRENCY, INCOMEMAX, INCOMEMIN, PRICE) values (12, 'PR1', 1, 70, 45, 'IT', 'M', 'EUR', 90000, 30000,75000);
insert into surveys (ID, PROVIDER_ID, SUBJECT_ID, AGEMAX, AGEMIN, COUNTRY, GENDER, INCOMECURRENCY, INCOMEMAX, INCOMEMIN, PRICE) values (13, 'PR1', 1, 70, 20, 'IT', 'B', 'USD', 150000, 18000,15000);
insert into surveys (ID, PROVIDER_ID, SUBJECT_ID, AGEMAX, AGEMIN, COUNTRY, GENDER, INCOMECURRENCY, INCOMEMAX, INCOMEMIN, PRICE) values (21, 'PR2', 1, 45, 20, 'IT', 'M', 'AUD', 50000, 18000,5000);
insert into surveys (ID, PROVIDER_ID, SUBJECT_ID, AGEMAX, AGEMIN, COUNTRY, GENDER, INCOMECURRENCY, INCOMEMAX, INCOMEMIN, PRICE) values (22, 'PR2', 1, 70, 45, 'IT', 'M', 'AUD', 90000, 30000,75000);
insert into surveys (ID, PROVIDER_ID, SUBJECT_ID, AGEMAX, AGEMIN, COUNTRY, GENDER, INCOMECURRENCY, INCOMEMAX, INCOMEMIN, PRICE) values (23, 'PR2', 1, 70, 20, 'IT', 'M', 'AUD', 150000, 18000,15000);
insert into surveys (ID, PROVIDER_ID, SUBJECT_ID, AGEMAX, AGEMIN, COUNTRY, GENDER, INCOMECURRENCY, INCOMEMAX, INCOMEMIN, PRICE) values (31, 'PR3', 1, 45, 20, 'IT', 'M', 'EUR', 50000, 18000,5000);
insert into surveys (ID, PROVIDER_ID, SUBJECT_ID, AGEMAX, AGEMIN, COUNTRY, GENDER, INCOMECURRENCY, INCOMEMAX, INCOMEMIN, PRICE) values (32, 'PR3', 1, 70, 45, 'IT', 'M', 'EUR', 90000, 30000,75000);
insert into surveys (ID, PROVIDER_ID, SUBJECT_ID, AGEMAX, AGEMIN, COUNTRY, GENDER, INCOMECURRENCY, INCOMEMAX, INCOMEMIN, PRICE) values (33, 'PR3', 1, 70, 20, 'IT', 'F', 'EUR', 150000, 18000,15000);
insert into surveys (ID, PROVIDER_ID, SUBJECT_ID, AGEMAX, AGEMIN, COUNTRY, GENDER, INCOMECURRENCY, INCOMEMAX, INCOMEMIN, PRICE) values (34, 'PR3', 1, 70, 20, 'IT', 'F', 'EUR', 150000, 18000,15000);

insert into requesters (ID, NAME, ADDRESS, API, EMAIL, FTP) values (1, 'Requester 1', 'Main Plaza, Gotham City', 'https://wayne.com/api/surveys', 'bm@wayne.com', 'ftp://wayne.com/cave');

insert into search_subscriptions (ID, REQUESTER_ID, CHANNELS, FREQUENCY, AGEMAX, AGEMIN, COUNTRIES, GENDER, INCOMECURRENCIES, INCOMEMAX, INCOMEMIN, PROVIDERS, SUBJECTS ) values(-1, 1, 'api, mail', 'weekly', '45', NULL, 'it,es', 'M', 'EUR', NULL, 20000, NULL, '1,2');
insert into search_subscriptions (ID, REQUESTER_ID, CHANNELS, FREQUENCY, AGEMAX, AGEMIN, COUNTRIES, GENDER, INCOMECURRENCIES, INCOMEMAX, INCOMEMIN, PROVIDERS, SUBJECTS ) values(-2, 1, 'api, MAIL', 'monthly', NULL, NULL, 'it', NULL, 'EUR', NULL, NULL, NULL, '3');

insert into survey_orders (ID, REQUESTER_ID, SURVEY_ID, STATUS ) values(-1, 1, 11, 'processing');
insert into survey_orders (ID, REQUESTER_ID, SURVEY_ID, STATUS ) values(-2, 1, 31, 'delivered');
