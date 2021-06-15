CREATE TABLE "public"."patient" (
  "record_id" int4 NOT NULL DEFAULT nextval('patient_patient_id_seq'::regclass),
  "radius_mean" numeric(255,4),
  "texture_mean" numeric(255,4),
  "perimeter_mean" numeric(255,4),
  "area_mean" numeric(255,4),
  "smoothness_mean" numeric(255,4),
  "compactness_mean" numeric(255,4),
  "concave_points_mean" numeric(255,4),
  "symmetry_mean" numeric(255,4),
  "fractal_dimension_mean" numeric(255,4),
  "radius_se" numeric(255,4),
  "texture_se" numeric(255,4),
  "perimeter_se" numeric(255,4),
  "area_se" numeric(255,4),
  "smoothness_se" numeric(255,4),
  "compactness_se" numeric(255,4),
  "concavity_se" numeric(255,4),
  "concave_points_se" numeric(255,4),
  "symmetry_seq" numeric(255,4),
  "fractal_dimension_se" numeric(255,4),
  "radius_worst" numeric(255,4),
  "texture_worst" numeric(255,4),
  "perimeter_worst" numeric(255,4),
  "area_worst" numeric(255,4),
  "smoothness_worst" numeric(255,4),
  "compactness_worst" numeric(255,4),
  "concavity_worst" numeric(255,4),
  "concave_points_worst" numeric(255,4),
  "symmetry_worst" numeric(255,4),
  "fractal_dimension_worst" numeric(255,4),
  "concavity_mean" numeric(255,4),
  "class" char(255) COLLATE "pg_catalog"."default" DEFAULT 'U'::bpchar,
  CONSTRAINT "patient_pkey" PRIMARY KEY ("record_id"),
  CONSTRAINT "check_class" CHECK ((class = ANY (ARRAY['B'::bpchar, 'M'::bpchar, 'U'::bpchar])))
)
;


CREATE TABLE "public"."patient_record" (
  "record_id" int4 NOT NULL DEFAULT nextval('patient_record_record_id_seq'::regclass),
  "radius_mean" numeric(255,4),
  "texture_mean" numeric(255,4),
  "perimeter_mean" numeric(255,4),
  "area_mean" numeric(255,4),
  "smoothness_mean" numeric(255,4),
  "compactness_mean" numeric(255,4),
  "concave_points_mean" numeric(255,4),
  "symmetry_mean" numeric(255,4),
  "fractal_dimension_mean" numeric(255,4),
  "radius_se" numeric(255,4),
  "texture_se" numeric(255,4),
  "perimeter_se" numeric(255,4),
  "area_se" numeric(255,4),
  "smoothness_se" numeric(255,4),
  "compactness_se" numeric(255,4),
  "concavity_se" numeric(255,4),
  "concave_points_se" numeric(255,4),
  "symmetry_seq" numeric(255,4),
  "fractal_dimension_se" numeric(255,4),
  "radius_worst" numeric(255,4),
  "texture_worst" numeric(255,4),
  "perimeter_worst" numeric(255,4),
  "area_worst" numeric(255,4),
  "smoothness_worst" numeric(255,4),
  "compactness_worst" numeric(255,4),
  "concavity_worst" numeric(255,4),
  "concave_points_worst" numeric(255,4),
  "symmetry_worst" numeric(255,4),
  "fractal_dimension_worst" numeric(255,4),
  "concavity_mean" numeric(255,4),
  "class" char(255) COLLATE "pg_catalog"."default",
  CONSTRAINT "patient_record_pkey" PRIMARY KEY ("record_id")
)
;


CREATE TABLE "public"."user_role" (
  "role_id" int4 NOT NULL,
  "name" varchar(255) COLLATE "pg_catalog"."default" NOT NULL,
  CONSTRAINT "user_role_pkey" PRIMARY KEY ("role_id")
)
;

CREATE TABLE "public"."user" (
  "user_id" int4 NOT NULL DEFAULT nextval('user_user_id_seq'::regclass),
  "user_name" varchar(255) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default",
  "role_id" int4,
  "is_new" bool NOT NULL DEFAULT true,
  "active" bool NOT NULL DEFAULT true,
  CONSTRAINT "user_pkey" PRIMARY KEY ("user_id"),
  CONSTRAINT "fk_role_user" FOREIGN KEY ("role_id") REFERENCES "public"."user_role" ("role_id") ON DELETE NO ACTION ON UPDATE RESTRICT
)
;


