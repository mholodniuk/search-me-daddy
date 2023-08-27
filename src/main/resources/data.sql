-- users
INSERT INTO public.users(username, display_name, email, "password")
VALUES('maciek', 'Maciej Hołodniuk', 'maciekholo@gmail.com', 'assword');

INSERT INTO public.users(username, display_name, email, "password")
VALUES('adam', 'Adam Kowalski', 'adamkowal@gmail.com', 'pass');

-- rooms
INSERT INTO public.rooms
("name", private, owner_id, created_at, modified_at)
VALUES('room', false, 1, NOW(), null);

-- documents
INSERT INTO public.documents
(id, "name", tags, content_type, storage_destination, file_path, room_id, owner_id, file_location_id, uploaded_at)
VALUES(gen_random_uuid(), 'Bazy danych projekt.pdf', '{}'::text[], 'application/pdf', 'LOCAL', '/tmp/s3/mock/Bazy danych projekt.pdf', 1, 1, 2, NOW());

INSERT INTO public.documents
(id, "name", tags, content_type, storage_destination, file_path, room_id, owner_id, file_location_id, uploaded_at)
VALUES(gen_random_uuid(), 'PRACA-INŻYNIERSKA.docx', '{}'::text[], 'application/vnd.openxmlformats-officedocument.wordprocessingml.document', 'LOCAL', '/tmp/s3/mock/PRACA-INŻYNIERSKA.docx', 1, 1, 1, NOW());


-- access keys
INSERT INTO public.access_keys
(id, "name", rights, valid_from, valid_to, room_id, issuer_id, participant_id)
VALUES(gen_random_uuid(), 'test', 'VIEWER', NOW(), NULL, 1, 1, 2);