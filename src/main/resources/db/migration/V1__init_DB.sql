create table author (
    id int8 generated by default as identity,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    primary key (id)
);

create table book (
    id int8 generated by default as identity,
    isbn varchar(255),
    publisher varchar(255),
    title varchar(255),
    primary key (id)
);

create table author_books (
    book_id int8 not null,
    author_id int8 not null,
    primary key (author_id, book_id)
);

create table client (
    id int8 generated by default as identity,
    email varchar(255) not null,
    first_name varchar(255) not null,
    last_name varchar(255) not null,
    passport int8 not null,
    primary key (id)
);

create table loan (
    id int8 generated by default as identity,
    loan_date date,
    return_date date,
    book_id int8,
    client_id int8,
    primary key (id)
);

alter table if exists author_books
    add constraint author_books_author_fk
    foreign key (author_id) references author;

alter table if exists author_books
    add constraint author_books_book_fk
    foreign key (book_id) references book;

alter table if exists loan
    add constraint loan_book_fk
    foreign key (book_id) references book;

alter table if exists loan
    add constraint loan_client_fk
    foreign key (client_id) references client;