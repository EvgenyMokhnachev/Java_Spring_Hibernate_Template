-- User
DROP SCHEMA IF EXISTS users CASCADE;
CREATE SCHEMA users;

CREATE TABLE users.patch (
  patch INTEGER
);
INSERT INTO users.patch (patch) VALUES (0);

CREATE TABLE users.users (
  id          BIGSERIAL PRIMARY KEY,
  email       VARCHAR(255) UNIQUE,
  username    VARCHAR(255) UNIQUE,
  password    TEXT,
  avatar      TEXT,
  role        VARCHAR(255),
  type        VARCHAR(255),
  slat        VARCHAR(255),
  key         UUID,
  registered  BOOLEAN,
  firstName   VARCHAR(255),
  lastName    VARCHAR(255),
  address     VARCHAR(255),
  phoneNumber VARCHAR(255),
  twitter     VARCHAR(255),
  linkedin    VARCHAR(255),
  skype       VARCHAR(255),
  facebook    VARCHAR(255),
  instagram   VARCHAR(255)
);

-- Audio
DROP SCHEMA IF EXISTS audio CASCADE;
CREATE SCHEMA audio;

CREATE TABLE audio.patch (
  patch INTEGER
);
INSERT INTO audio.patch (patch) VALUES (0);

CREATE TABLE audio.audios (
  id                   BIGSERIAL PRIMARY KEY,
  project_id           BIGINT,
  name                 VARCHAR(255),
  original_file_id     BIGINT,
  protected_file128_id BIGINT,
  protected_file256_id BIGINT,
  protected_file320_id BIGINT,
  json_samples_file_id BIGINT,
  date                 BIGINT,
  revised              BOOLEAN
);

CREATE TABLE audio.watermarks (
  id              BIGSERIAL PRIMARY KEY,
  name            VARCHAR(255),
  intervalStr     VARCHAR(255),
  intervalSeconds INT,
  file_id         BIGINT,
  author_id       BIGINT,
  createdDate     BIGINT
);

-- Project
DROP SCHEMA IF EXISTS project CASCADE;
CREATE SCHEMA project;

CREATE TABLE project.patch (
  patch INTEGER
);
INSERT INTO project.patch (patch) VALUES (0);

CREATE TABLE project.projects (
  id          BIGSERIAL PRIMARY KEY,
  author_id   BIGINT,
  name        VARCHAR(255),
  description TEXT,
  date        BIGINT,
  deadline    BIGINT,
  completed   BOOLEAN
);

CREATE TABLE project.notes (
  id         BIGSERIAL PRIMARY KEY,
  user_id    BIGINT,
  project_id BIGINT,
  subject    VARCHAR(255),
  message    TEXT,
  date       BIGINT
);

CREATE TABLE project.messages (
  id            BIGSERIAL PRIMARY KEY,
  text          TEXT,
  date          BIGINT,
  author_id     BIGINT,
  parent_id     BIGINT,
  discussion_id BIGINT,
  comment_id    BIGINT
);

CREATE TABLE project.discussions (
  id          BIGSERIAL PRIMARY KEY,
  project_id  BIGINT,
  subject     VARCHAR(255),
  description TEXT,
  author_id   BIGINT,
  date        BIGINT
);

CREATE TABLE project.files (
  id            BIGSERIAL PRIMARY KEY,
  user_id       BIGINT,
  project_id    BIGINT,
  discussion_id BIGINT,
  note_id       BIGINT,
  todo_task_id  BIGINT,
  name          VARCHAR(255),
  path          TEXT,
  size          INTEGER,
  extension     VARCHAR(255),
  uploaded_date BIGINT,
  downloadHref  VARCHAR(255),
  UUIDFileName  VARCHAR(255)
);

CREATE TABLE project.todo_lists (
  id          BIGSERIAL PRIMARY KEY,
  title       VARCHAR(255),
  description TEXT,
  date        BIGINT,
  project_id  BIGINT,
  author_id   BIGINT
);

CREATE TABLE project.todo_tasks (
  id            BIGSERIAL PRIMARY KEY,
  title         VARCHAR(255),
  completed     BOOLEAN,
  description   TEXT,
  date          BIGINT,
  deadline_date BIGINT,
  project_id    BIGINT,
  author_id     BIGINT,
  todo_list_id  BIGINT,
  visible       VARCHAR(255)
);

CREATE TABLE project.projects_customers (
  project_id BIGINT,
  user_id    BIGINT
);

CREATE TABLE project.projects_executors (
  project_id BIGINT,
  user_id    BIGINT
);

CREATE TABLE project.todo_tasks_customers (
  task_id BIGINT,
  user_id BIGINT
);

CREATE TABLE project.todo_tasks_executors (
  task_id BIGINT,
  user_id BIGINT
);

CREATE TABLE project.deadlines (
  id         BIGSERIAL PRIMARY KEY,
  project_id BIGINT,
  date       BIGINT
);

CREATE TABLE project.invites (
  id         BIGSERIAL PRIMARY KEY,
  email      VARCHAR(255),
  project_id BIGINT,
  key        UUID,
  role       VARCHAR(255)
);

CREATE TABLE audio.comments (
  id             BIGSERIAL PRIMARY KEY,
  audio_id       BIGSERIAL,
  author_id      BIGSERIAL,
  date           BIGINT,
  resolved       BOOLEAN,
  left_percents  DOUBLE PRECISION,
  right_percents DOUBLE PRECISION,
  ranged         BOOLEAN
);

CREATE TABLE project.events (
  id         BIGSERIAL PRIMARY KEY,
  author_id  BIGINT,
  project_id BIGINT,
  date       BIGINT,
  action     VARCHAR(255),
  entity     VARCHAR(255)
);

CREATE TABLE project.unread_messages (
  id         BIGSERIAL PRIMARY KEY,
  user_id    BIGINT,
  message_id BIGINT
);

ALTER TABLE audio.audios
ADD CONSTRAINT audio_project_fk
FOREIGN KEY (project_id)
REFERENCES project.projects
ON DELETE CASCADE;

ALTER TABLE audio.audios
ADD CONSTRAINT audio_orig_file_fk
FOREIGN KEY (original_file_id)
REFERENCES project.files
ON DELETE CASCADE;

ALTER TABLE audio.audios
ADD CONSTRAINT audio_protect128_file_fk
FOREIGN KEY (protected_file128_id)
REFERENCES project.files
ON DELETE CASCADE;

ALTER TABLE audio.audios
ADD CONSTRAINT audio_protect256_file_fk
FOREIGN KEY (protected_file256_id)
REFERENCES project.files
ON DELETE CASCADE;

ALTER TABLE audio.audios
ADD CONSTRAINT audio_protect320_file_fk
FOREIGN KEY (protected_file320_id)
REFERENCES project.files
ON DELETE CASCADE;

ALTER TABLE audio.audios
ADD CONSTRAINT audio_sample_file_fk
FOREIGN KEY (json_samples_file_id)
REFERENCES project.files
ON DELETE CASCADE;

ALTER TABLE project.projects
ADD CONSTRAINT project_user_fk
FOREIGN KEY (author_id)
REFERENCES users.users
ON DELETE CASCADE;

ALTER TABLE project.notes
ADD CONSTRAINT note_project_fk
FOREIGN KEY (project_id)
REFERENCES project.projects
ON DELETE CASCADE;

ALTER TABLE project.notes
ADD CONSTRAINT note_user_fk
FOREIGN KEY (user_id)
REFERENCES users.users
ON DELETE CASCADE;

ALTER TABLE project.messages
ADD CONSTRAINT message_user_fk
FOREIGN KEY (author_id)
REFERENCES users.users
ON DELETE CASCADE;

ALTER TABLE project.messages
ADD CONSTRAINT message_message_fk
FOREIGN KEY (parent_id)
REFERENCES project.messages
ON DELETE CASCADE;

ALTER TABLE project.messages
ADD CONSTRAINT message_discussion_fk
FOREIGN KEY (discussion_id)
REFERENCES project.discussions
ON DELETE CASCADE;

ALTER TABLE project.messages
ADD CONSTRAINT message_comment_fk
FOREIGN KEY (comment_id)
REFERENCES audio.comments
ON DELETE CASCADE;

ALTER TABLE project.discussions
ADD CONSTRAINT discussion_project_fk
FOREIGN KEY (project_id)
REFERENCES project.projects
ON DELETE CASCADE;

ALTER TABLE project.files
ADD CONSTRAINT files_project_fk
FOREIGN KEY (project_id)
REFERENCES project.projects
ON DELETE CASCADE;

ALTER TABLE project.files
ADD CONSTRAINT files_discussion_fk
FOREIGN KEY (discussion_id)
REFERENCES project.discussions
ON DELETE CASCADE;

ALTER TABLE project.files
ADD CONSTRAINT files_note_fk
FOREIGN KEY (note_id)
REFERENCES project.notes
ON DELETE CASCADE;

ALTER TABLE project.files
ADD CONSTRAINT files_user_fk
FOREIGN KEY (user_id)
REFERENCES users.users
ON DELETE CASCADE;

ALTER TABLE project.files
ADD CONSTRAINT files_todo_task_fk
FOREIGN KEY (todo_task_id)
REFERENCES project.todo_tasks
ON DELETE CASCADE;

ALTER TABLE project.todo_lists
ADD CONSTRAINT todo_lists_project_fk
FOREIGN KEY (project_id)
REFERENCES project.projects
ON DELETE CASCADE;

ALTER TABLE project.todo_lists
ADD CONSTRAINT todo_lists_user_fk
FOREIGN KEY (author_id)
REFERENCES users.users
ON DELETE CASCADE;

ALTER TABLE project.todo_tasks
ADD CONSTRAINT todo_tasks_project_fk
FOREIGN KEY (project_id)
REFERENCES project.projects
ON DELETE CASCADE;

ALTER TABLE project.todo_tasks
ADD CONSTRAINT todo_tasks_user_fk
FOREIGN KEY (author_id)
REFERENCES users.users
ON DELETE CASCADE;

ALTER TABLE project.todo_tasks
ADD CONSTRAINT todo_tasks_todo_list_fk
FOREIGN KEY (todo_list_id)
REFERENCES project.todo_lists
ON DELETE CASCADE;

ALTER TABLE project.projects_customers
ADD CONSTRAINT customers_project_fk
FOREIGN KEY (project_id)
REFERENCES project.projects
ON DELETE CASCADE;

ALTER TABLE project.projects_customers
ADD CONSTRAINT customers_user_fk
FOREIGN KEY (user_id)
REFERENCES users.users
ON DELETE CASCADE;

ALTER TABLE project.projects_executors
ADD CONSTRAINT executors_project_fk
FOREIGN KEY (project_id)
REFERENCES project.projects
ON DELETE CASCADE;

ALTER TABLE project.projects_executors
ADD CONSTRAINT executors_user_fk
FOREIGN KEY (user_id)
REFERENCES users.users
ON DELETE CASCADE;

ALTER TABLE project.todo_tasks_executors
ADD CONSTRAINT executors_task_fk
FOREIGN KEY (task_id)
REFERENCES project.todo_tasks
ON DELETE CASCADE;

ALTER TABLE project.todo_tasks_executors
ADD CONSTRAINT executors_task_user_fk
FOREIGN KEY (user_id)
REFERENCES users.users
ON DELETE CASCADE;

ALTER TABLE project.todo_tasks_customers
ADD CONSTRAINT customers_task_fk
FOREIGN KEY (task_id)
REFERENCES project.todo_tasks
ON DELETE CASCADE;

ALTER TABLE project.todo_tasks_customers
ADD CONSTRAINT customers_task_user_fk
FOREIGN KEY (user_id)
REFERENCES users.users
ON DELETE CASCADE;

ALTER TABLE project.deadlines
ADD CONSTRAINT deadline_project_fk
FOREIGN KEY (project_id)
REFERENCES project.projects
ON DELETE CASCADE;

ALTER TABLE project.invites
ADD CONSTRAINT invite_project_fk
FOREIGN KEY (project_id)
REFERENCES project.projects
ON DELETE CASCADE;

ALTER TABLE audio.comments
ADD CONSTRAINT comment_user_fk
FOREIGN KEY (author_id)
REFERENCES users.users
ON DELETE CASCADE;

ALTER TABLE audio.comments
ADD CONSTRAINT comment_audio_fk
FOREIGN KEY (audio_id)
REFERENCES audio.audios
ON DELETE CASCADE;

ALTER TABLE project.events
ADD CONSTRAINT event_project_fk
FOREIGN KEY (project_id)
REFERENCES project.projects
ON DELETE CASCADE;

ALTER TABLE project.events
ADD CONSTRAINT event_user_fk
FOREIGN KEY (author_id)
REFERENCES users.users
ON DELETE CASCADE;

ALTER TABLE project.unread_messages
ADD CONSTRAINT unread_user_fk
FOREIGN KEY (user_id)
REFERENCES users.users
ON DELETE CASCADE;

ALTER TABLE project.unread_messages
ADD CONSTRAINT unread_message_fk
FOREIGN KEY (message_id)
REFERENCES project.messages
ON DELETE CASCADE;

