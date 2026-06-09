--
-- PostgreSQL database dump
--

\restrict 17dEFlYtsB6kd7dU0X4gczQNy1u23Sw4C1Cec8lauHBmF9ddL05hNwmn5eZK3b9

-- Dumped from database version 18.4 (Debian 18.4-1.pgdg12+1)
-- Dumped by pg_dump version 18.3 (Ubuntu 18.3-1.pgdg24.04+1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET transaction_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

--
-- Name: public; Type: SCHEMA; Schema: -; Owner: root
--

-- *not* creating schema, since initdb creates it


ALTER SCHEMA public OWNER TO root;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: administrador; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.administrador (
    id_administrador integer NOT NULL,
    id_usuario integer NOT NULL,
    nivel_acceso character varying(100)
);


ALTER TABLE public.administrador OWNER TO root;

--
-- Name: administrador_id_administrador_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.administrador_id_administrador_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.administrador_id_administrador_seq OWNER TO root;

--
-- Name: administrador_id_administrador_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.administrador_id_administrador_seq OWNED BY public.administrador.id_administrador;


--
-- Name: comprador; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.comprador (
    id_comprador integer NOT NULL,
    id_usuario integer NOT NULL
);


ALTER TABLE public.comprador OWNER TO root;

--
-- Name: comprador_id_comprador_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.comprador_id_comprador_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.comprador_id_comprador_seq OWNER TO root;

--
-- Name: comprador_id_comprador_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.comprador_id_comprador_seq OWNED BY public.comprador.id_comprador;


--
-- Name: contrato; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.contrato (
    id_contrato integer NOT NULL,
    fecha_inicio date,
    fecha_final date,
    monto_total integer,
    estado_contrato character varying(100),
    id_vendedor integer NOT NULL,
    id_comprador integer NOT NULL,
    id_propiedad integer NOT NULL
);


ALTER TABLE public.contrato OWNER TO root;

--
-- Name: contrato_id_contrato_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.contrato_id_contrato_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.contrato_id_contrato_seq OWNER TO root;

--
-- Name: contrato_id_contrato_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.contrato_id_contrato_seq OWNED BY public.contrato.id_contrato;


--
-- Name: pago; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.pago (
    id_pago integer NOT NULL,
    monto integer,
    fecha_pago date,
    estado_pago character varying(100),
    metodo_pago character varying(100),
    id_contrato integer NOT NULL
);


ALTER TABLE public.pago OWNER TO root;

--
-- Name: pago_id_pago_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.pago_id_pago_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.pago_id_pago_seq OWNER TO root;

--
-- Name: pago_id_pago_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.pago_id_pago_seq OWNED BY public.pago.id_pago;


--
-- Name: propiedad; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.propiedad (
    id_propiedad integer NOT NULL,
    titulo character varying(255),
    descripcion text,
    precio numeric,
    area character varying(255),
    bathrooms integer,
    bedrooms integer,
    type character varying(50),
    ubicacion character varying(255),
    id_vendedor integer
);


ALTER TABLE public.propiedad OWNER TO root;

--
-- Name: propiedad_id_propiedad_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.propiedad_id_propiedad_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.propiedad_id_propiedad_seq OWNER TO root;

--
-- Name: propiedad_id_propiedad_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.propiedad_id_propiedad_seq OWNED BY public.propiedad.id_propiedad;


--
-- Name: propiedad_images; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.propiedad_images (
    id_propiedad integer NOT NULL,
    image_url character varying(255) NOT NULL
);


ALTER TABLE public.propiedad_images OWNER TO root;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.usuario (
    id_usuario integer NOT NULL,
    nombre character varying(255),
    email character varying(255) NOT NULL,
    password character varying(255) NOT NULL,
    rol character varying(50),
    cedula character varying(50)
);


ALTER TABLE public.usuario OWNER TO root;

--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.usuario_id_usuario_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.usuario_id_usuario_seq OWNER TO root;

--
-- Name: usuario_id_usuario_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.usuario_id_usuario_seq OWNED BY public.usuario.id_usuario;


--
-- Name: vendedor; Type: TABLE; Schema: public; Owner: root
--

CREATE TABLE public.vendedor (
    id_vendedor integer NOT NULL,
    id_usuario integer NOT NULL
);


ALTER TABLE public.vendedor OWNER TO root;

--
-- Name: vendedor_id_vendedor_seq; Type: SEQUENCE; Schema: public; Owner: root
--

CREATE SEQUENCE public.vendedor_id_vendedor_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER SEQUENCE public.vendedor_id_vendedor_seq OWNER TO root;

--
-- Name: vendedor_id_vendedor_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: root
--

ALTER SEQUENCE public.vendedor_id_vendedor_seq OWNED BY public.vendedor.id_vendedor;


--
-- Name: administrador id_administrador; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.administrador ALTER COLUMN id_administrador SET DEFAULT nextval('public.administrador_id_administrador_seq'::regclass);


--
-- Name: comprador id_comprador; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.comprador ALTER COLUMN id_comprador SET DEFAULT nextval('public.comprador_id_comprador_seq'::regclass);


--
-- Name: contrato id_contrato; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.contrato ALTER COLUMN id_contrato SET DEFAULT nextval('public.contrato_id_contrato_seq'::regclass);


--
-- Name: pago id_pago; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.pago ALTER COLUMN id_pago SET DEFAULT nextval('public.pago_id_pago_seq'::regclass);


--
-- Name: propiedad id_propiedad; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.propiedad ALTER COLUMN id_propiedad SET DEFAULT nextval('public.propiedad_id_propiedad_seq'::regclass);


--
-- Name: usuario id_usuario; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.usuario ALTER COLUMN id_usuario SET DEFAULT nextval('public.usuario_id_usuario_seq'::regclass);


--
-- Name: vendedor id_vendedor; Type: DEFAULT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.vendedor ALTER COLUMN id_vendedor SET DEFAULT nextval('public.vendedor_id_vendedor_seq'::regclass);


--
-- Name: administrador administrador_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.administrador
    ADD CONSTRAINT administrador_pkey PRIMARY KEY (id_administrador);


--
-- Name: comprador comprador_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.comprador
    ADD CONSTRAINT comprador_pkey PRIMARY KEY (id_comprador);


--
-- Name: contrato contrato_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.contrato
    ADD CONSTRAINT contrato_pkey PRIMARY KEY (id_contrato);


--
-- Name: pago pago_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.pago
    ADD CONSTRAINT pago_pkey PRIMARY KEY (id_pago);


--
-- Name: propiedad propiedad_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.propiedad
    ADD CONSTRAINT propiedad_pkey PRIMARY KEY (id_propiedad);


--
-- Name: usuario usuario_email_key; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_email_key UNIQUE (email);


--
-- Name: usuario usuario_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.usuario
    ADD CONSTRAINT usuario_pkey PRIMARY KEY (id_usuario);


--
-- Name: vendedor vendedor_pkey; Type: CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.vendedor
    ADD CONSTRAINT vendedor_pkey PRIMARY KEY (id_vendedor);


--
-- Name: administrador fk_administrador_usuario; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.administrador
    ADD CONSTRAINT fk_administrador_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario) ON DELETE CASCADE;


--
-- Name: comprador fk_comprador_usuario; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.comprador
    ADD CONSTRAINT fk_comprador_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario) ON DELETE CASCADE;


--
-- Name: contrato fk_contrato_comprador; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.contrato
    ADD CONSTRAINT fk_contrato_comprador FOREIGN KEY (id_comprador) REFERENCES public.comprador(id_comprador) ON DELETE RESTRICT;


--
-- Name: contrato fk_contrato_propiedad; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.contrato
    ADD CONSTRAINT fk_contrato_propiedad FOREIGN KEY (id_propiedad) REFERENCES public.propiedad(id_propiedad) ON DELETE RESTRICT;


--
-- Name: contrato fk_contrato_vendedor; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.contrato
    ADD CONSTRAINT fk_contrato_vendedor FOREIGN KEY (id_vendedor) REFERENCES public.vendedor(id_vendedor) ON DELETE RESTRICT;


--
-- Name: pago fk_pago_contrato; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.pago
    ADD CONSTRAINT fk_pago_contrato FOREIGN KEY (id_contrato) REFERENCES public.contrato(id_contrato) ON DELETE CASCADE;


--
-- Name: propiedad_images fk_propiedad_images_propiedad; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.propiedad_images
    ADD CONSTRAINT fk_propiedad_images_propiedad FOREIGN KEY (id_propiedad) REFERENCES public.propiedad(id_propiedad) ON DELETE CASCADE;


--
-- Name: propiedad fk_propiedad_vendedor; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.propiedad
    ADD CONSTRAINT fk_propiedad_vendedor FOREIGN KEY (id_vendedor) REFERENCES public.usuario(id_usuario) ON DELETE CASCADE;


--
-- Name: vendedor fk_vendedor_usuario; Type: FK CONSTRAINT; Schema: public; Owner: root
--

ALTER TABLE ONLY public.vendedor
    ADD CONSTRAINT fk_vendedor_usuario FOREIGN KEY (id_usuario) REFERENCES public.usuario(id_usuario) ON DELETE CASCADE;


--
-- Name: DEFAULT PRIVILEGES FOR SEQUENCES; Type: DEFAULT ACL; Schema: -; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON SEQUENCES TO root;


--
-- Name: DEFAULT PRIVILEGES FOR TYPES; Type: DEFAULT ACL; Schema: -; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON TYPES TO root;


--
-- Name: DEFAULT PRIVILEGES FOR FUNCTIONS; Type: DEFAULT ACL; Schema: -; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON FUNCTIONS TO root;


--
-- Name: DEFAULT PRIVILEGES FOR TABLES; Type: DEFAULT ACL; Schema: -; Owner: postgres
--

ALTER DEFAULT PRIVILEGES FOR ROLE postgres GRANT ALL ON TABLES TO root;


--
-- PostgreSQL database dump complete
--

\unrestrict 17dEFlYtsB6kd7dU0X4gczQNy1u23Sw4C1Cec8lauHBmF9ddL05hNwmn5eZK3b9

