
create table real_estate_avg_prices (
  id integer generated by default as identity(start with 1, increment by 1) primary key,
  avg_price integer,
  coord_long decimal(10,5),
  coord_lat  decimal(10,5)
);

create table price_estimates (
  id integer generated by default as identity(start with 1, increment by 1) primary key,
  area  decimal(5,2),
  price integer,
  coord_long decimal(10,5),
  coord_lat  decimal(10,5)
);