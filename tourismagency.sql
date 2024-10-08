PGDMP                      |            tourismagency    16.2    16.2                0    0    ENCODING    ENCODING        SET client_encoding = 'UTF8';
                      false                       0    0 
   STDSTRINGS 
   STDSTRINGS     (   SET standard_conforming_strings = 'on';
                      false                       0    0 
   SEARCHPATH 
   SEARCHPATH     8   SELECT pg_catalog.set_config('search_path', '', false);
                      false                       1262    17056    tourismagency    DATABASE     �   CREATE DATABASE tourismagency WITH TEMPLATE = template0 ENCODING = 'UTF8' LOCALE_PROVIDER = libc LOCALE = 'English_United States.1252';
    DROP DATABASE tourismagency;
                postgres    false            �            1259    17111    user    TABLE     �   CREATE TABLE public."user" (
    user_id integer NOT NULL,
    user_name text NOT NULL,
    user_pass text NOT NULL,
    user_role text NOT NULL
);
    DROP TABLE public."user";
       public         heap    postgres    false            �            1259    17110    user_id_seq    SEQUENCE     �   CREATE SEQUENCE public.user_id_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 "   DROP SEQUENCE public.user_id_seq;
       public          postgres    false    222                       0    0    user_id_seq    SEQUENCE OWNED BY     B   ALTER SEQUENCE public.user_id_seq OWNED BY public."user".user_id;
          public          postgres    false    221            �            1259    17099    hotel    TABLE     t  CREATE TABLE public.hotel (
    name text NOT NULL,
    address text NOT NULL,
    mail text NOT NULL,
    phone text NOT NULL,
    star text NOT NULL,
    car_park boolean,
    wifi boolean,
    pool boolean,
    fitness boolean,
    concierge boolean,
    spa boolean,
    room_service boolean,
    id integer DEFAULT nextval('public.user_id_seq'::regclass) NOT NULL
);
    DROP TABLE public.hotel;
       public         heap    postgres    false    221            �            1259    17064    hotel_pension    TABLE     �   CREATE TABLE public.hotel_pension (
    id integer DEFAULT nextval('public.user_id_seq'::regclass) NOT NULL,
    hotel_id integer NOT NULL,
    pension_type text NOT NULL
);
 !   DROP TABLE public.hotel_pension;
       public         heap    postgres    false    221            �            1259    17085    hotel_season    TABLE     �   CREATE TABLE public.hotel_season (
    id integer DEFAULT nextval('public.user_id_seq'::regclass) NOT NULL,
    hotel_id integer NOT NULL,
    start_date date NOT NULL,
    finish_date date NOT NULL
);
     DROP TABLE public.hotel_season;
       public         heap    postgres    false    221            �            1259    17071    reservation    TABLE     �  CREATE TABLE public.reservation (
    id integer DEFAULT nextval('public.user_id_seq'::regclass) NOT NULL,
    room_id integer NOT NULL,
    check_in_date date NOT NULL,
    total_price integer NOT NULL,
    guest_count integer NOT NULL,
    guest_name text NOT NULL,
    guest_citizen_id text NOT NULL,
    guest_mail text NOT NULL,
    guest_phone text NOT NULL,
    check_out_date date NOT NULL
);
    DROP TABLE public.reservation;
       public         heap    postgres    false    221            �            1259    17078    room    TABLE     �  CREATE TABLE public.room (
    id integer DEFAULT nextval('public.user_id_seq'::regclass) NOT NULL,
    hotel_id integer NOT NULL,
    pension_id integer NOT NULL,
    season_id integer NOT NULL,
    type text NOT NULL,
    stock integer NOT NULL,
    adult_price integer NOT NULL,
    child_price integer NOT NULL,
    bed_capacity integer NOT NULL,
    square_meter integer NOT NULL,
    television boolean,
    minibar boolean,
    game_console boolean,
    cash_box boolean,
    projection boolean
);
    DROP TABLE public.room;
       public         heap    postgres    false    221            �            1259    17107    season_id_seq    SEQUENCE     v   CREATE SEQUENCE public.season_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;
 $   DROP SEQUENCE public.season_id_seq;
       public          postgres    false            j           2604    17114    user user_id    DEFAULT     i   ALTER TABLE ONLY public."user" ALTER COLUMN user_id SET DEFAULT nextval('public.user_id_seq'::regclass);
 =   ALTER TABLE public."user" ALTER COLUMN user_id DROP DEFAULT;
       public          postgres    false    221    222    222            
          0    17099    hotel 
   TABLE DATA           �   COPY public.hotel (name, address, mail, phone, star, car_park, wifi, pool, fitness, concierge, spa, room_service, id) FROM stdin;
    public          postgres    false    219   �!                 0    17064    hotel_pension 
   TABLE DATA           C   COPY public.hotel_pension (id, hotel_id, pension_type) FROM stdin;
    public          postgres    false    215   �!       	          0    17085    hotel_season 
   TABLE DATA           M   COPY public.hotel_season (id, hotel_id, start_date, finish_date) FROM stdin;
    public          postgres    false    218   '"                 0    17071    reservation 
   TABLE DATA           �   COPY public.reservation (id, room_id, check_in_date, total_price, guest_count, guest_name, guest_citizen_id, guest_mail, guest_phone, check_out_date) FROM stdin;
    public          postgres    false    216   b"                 0    17078    room 
   TABLE DATA           �   COPY public.room (id, hotel_id, pension_id, season_id, type, stock, adult_price, child_price, bed_capacity, square_meter, television, minibar, game_console, cash_box, projection) FROM stdin;
    public          postgres    false    217   �"                 0    17111    user 
   TABLE DATA           J   COPY public."user" (user_id, user_name, user_pass, user_role) FROM stdin;
    public          postgres    false    222   �"                  0    0    season_id_seq    SEQUENCE SET     <   SELECT pg_catalog.setval('public.season_id_seq', 1, false);
          public          postgres    false    220                       0    0    user_id_seq    SEQUENCE SET     ;   SELECT pg_catalog.setval('public.user_id_seq', 116, true);
          public          postgres    false    221            l           2606    17070     hotel_pension hotel_pension_pkey 
   CONSTRAINT     ^   ALTER TABLE ONLY public.hotel_pension
    ADD CONSTRAINT hotel_pension_pkey PRIMARY KEY (id);
 J   ALTER TABLE ONLY public.hotel_pension DROP CONSTRAINT hotel_pension_pkey;
       public            postgres    false    215            t           2606    17109    hotel hotel_pkey 
   CONSTRAINT     N   ALTER TABLE ONLY public.hotel
    ADD CONSTRAINT hotel_pkey PRIMARY KEY (id);
 :   ALTER TABLE ONLY public.hotel DROP CONSTRAINT hotel_pkey;
       public            postgres    false    219            r           2606    17091    hotel_season hotel_season_pkey 
   CONSTRAINT     \   ALTER TABLE ONLY public.hotel_season
    ADD CONSTRAINT hotel_season_pkey PRIMARY KEY (id);
 H   ALTER TABLE ONLY public.hotel_season DROP CONSTRAINT hotel_season_pkey;
       public            postgres    false    218            n           2606    17077    reservation reservation_pkey 
   CONSTRAINT     Z   ALTER TABLE ONLY public.reservation
    ADD CONSTRAINT reservation_pkey PRIMARY KEY (id);
 F   ALTER TABLE ONLY public.reservation DROP CONSTRAINT reservation_pkey;
       public            postgres    false    216            p           2606    17084    room room_pkey 
   CONSTRAINT     L   ALTER TABLE ONLY public.room
    ADD CONSTRAINT room_pkey PRIMARY KEY (id);
 8   ALTER TABLE ONLY public.room DROP CONSTRAINT room_pkey;
       public            postgres    false    217            v           2606    17116    user user_pkey 
   CONSTRAINT     S   ALTER TABLE ONLY public."user"
    ADD CONSTRAINT user_pkey PRIMARY KEY (user_id);
 :   ALTER TABLE ONLY public."user" DROP CONSTRAINT user_pkey;
       public            postgres    false    222            
   ;   x�K�426� ��� r b���\NC#c �4�L�0����8A���9W� ��         2   x�340�440���˩THJM�24�
�s����(��g��q��qqq �C
�      	   +   x�340�440�4202�54"$&���P�$d�)���� n�
�         +   x�344�444�4202�54"NNN#02�4B������� �J�         >   x�340�440bS 6���K�IU����4�4���244 �3bK ���>�b���� =��         2   x�3�L��܂����T.cs���N����27�L� 
�6F��� t^     