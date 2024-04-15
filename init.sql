-- public.customer definition

-- Drop table

-- DROP TABLE public.customer;

CREATE TABLE public.customer (
	customer_id serial4 NOT NULL,
	"name" varchar(255) NOT NULL,
	address varchar(255) NULL,
	phone varchar(20) NULL,
	CONSTRAINT customer_pkey PRIMARY KEY (customer_id)
);


-- public.movement_type definition

-- Drop table

-- DROP TABLE public.movement_type;

CREATE TABLE public.movement_type (
	movement_type_id serial4 NOT NULL,
	description varchar(50) NOT NULL,
	CONSTRAINT movementtype_pkey PRIMARY KEY (movement_type_id)
);


-- public.account definition

-- Drop table

-- DROP TABLE public.account;

CREATE TABLE public.account (
	account_id serial4 NOT NULL,
	customer_id int4 NOT NULL,
	"number" varchar(20) NOT NULL,
	balance numeric(10, 2) NOT NULL,
	CONSTRAINT account_number_key UNIQUE (number),
	CONSTRAINT account_pkey PRIMARY KEY (account_id),
	CONSTRAINT account_customer_id_fkey FOREIGN KEY (customer_id) REFERENCES public.customer(customer_id)
);


-- public.movement definition

-- Drop table

-- DROP TABLE public.movement;

CREATE TABLE public.movement (
	movement_id serial4 NOT NULL,
	account_id int4 NOT NULL,
	movement_type_id int4 NOT NULL,
	movement_date date NOT NULL,
	amount numeric(10, 2) NOT NULL,
	CONSTRAINT movement_pkey PRIMARY KEY (movement_id),
	CONSTRAINT movement_account_id_fkey FOREIGN KEY (account_id) REFERENCES public.account(account_id),
	CONSTRAINT movement_movement_type_id_fkey FOREIGN KEY (movement_type_id) REFERENCES public.movement_type(movement_type_id)
);

INSERT INTO public.customer
(customer_id, "name", address, phone)
VALUES(1, 'christian avalos', 'Santa Ana, cadiz', '7662-2150');

INSERT INTO public.account
(account_id, customer_id, "number", balance)
VALUES(1, 1, '123456789', 1200.00);

INSERT INTO public.movement_type
(movement_type_id, description)
VALUES(1, 'DEBITO');
INSERT INTO public.movement_type
(movement_type_id, description)
VALUES(2, 'CREDITO');