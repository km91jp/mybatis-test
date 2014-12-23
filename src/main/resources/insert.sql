CREATE TABLE mybatis_test
(
  id integer NOT NULL, -- id.
  name text, -- name.
  create_date timestamp with time zone,
  mod_date timestamp with time zone, -- date of modification
  CONSTRAINT pkey PRIMARY KEY (id )
);

CREATE TABLE blog
(
  id integer NOT NULL,
  title text,
  author text,
  CONSTRAINT pkey_id PRIMARY KEY (id )
);

CREATE TABLE post
(
  id integer NOT NULL,
  title text,
  text text,
  blog_id integer,
  CONSTRAINT pkey_post_id PRIMARY KEY (id ),
  CONSTRAINT fkey_blog_id FOREIGN KEY (blog_id)
      REFERENCES blog (id) MATCH SIMPLE
      ON UPDATE NO ACTION ON DELETE NO ACTION
);

insert into blog values (1, 'blog1', 'author1');
insert into blog values (2, 'blog2', 'author2');
insert into blog values (3, 'blog3', 'author3');
insert into blog values (4, 'blog4', 'author4');
insert into blog values (5, 'blog5', 'author5');

insert into post values (1, 'blog1_text1', 'text1', 1);
insert into post values (2, 'blog1_text2', 'text2', 1);
insert into post values (3, 'blog1_text3', 'text3', 1);
insert into post values (4, 'blog2_text1', 'text4', 2);
insert into post values (5, 'blog2_text2', 'text5', 2);
insert into post values (6, 'blog2_text3', 'text6', 2);
insert into post values (7, 'blog3_text1', 'text7', 3);
insert into post values (8, 'blog4_text1', 'text8', 4);
insert into post values (9, 'blog5_text1', 'text9', 5);

