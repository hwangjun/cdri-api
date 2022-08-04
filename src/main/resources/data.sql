/* 카테고리 추가 */
INSERT INTO category(name, created_at, updated_at) VALUES('문학', NOW(), NOW());
INSERT INTO category(name, created_at, updated_at) VALUES('경제경영', NOW(), NOW());
INSERT INTO category(name, created_at, updated_at) VALUES('인문학', NOW(), NOW());
INSERT INTO category(name, created_at, updated_at) VALUES('IT', NOW(), NOW());
INSERT INTO category(name, created_at, updated_at) VALUES('과학', NOW(), NOW());

/* 도서 추가 */
INSERT INTO book(author, title, is_rental_available, book_status, category_id, created_at, updated_at)
VALUES('권태영', '너에게 해주지 못한 말들', 'Y', 'NORMAL', 1, NOW(), NOW());
INSERT INTO book(author, title, is_rental_available, book_status, category_id, created_at, updated_at)
VALUES('현영서', '단순하게 배부르게', 'Y', 'NORMAL', 1, NOW(), NOW());
INSERT INTO book(author, title, is_rental_available, book_status, category_id, created_at, updated_at)
VALUES('권태영', '게으른 사랑', 'Y', 'NORMAL', 1, NOW(), NOW());

INSERT INTO book(author, title, is_rental_available, book_status, category_id, created_at, updated_at)
VALUES('권태영', '트랜드 코리아 2322', 'Y', 'NORMAL', 2, NOW(), NOW());
INSERT INTO book(author, title, is_rental_available, book_status, category_id, created_at, updated_at)
VALUES('장동혁', '초격자 투자', 'Y', 'NORMAL', 2, NOW(), NOW());
INSERT INTO book(author, title, is_rental_available, book_status, category_id, created_at, updated_at)
VALUES('홍길동', '파이어족 강환국의 하면 되지 않는다! 퀀트 투자', 'Y', 'NORMAL', 2, NOW(), NOW());

INSERT INTO book(author, title, is_rental_available, book_status, category_id, created_at, updated_at)
VALUES('이서연', '진심보다 밥', 'Y', 'NORMAL', 3, NOW(), NOW());
INSERT INTO book(author, title, is_rental_available, book_status, category_id, created_at, updated_at)
VALUES('위성원', '실패에 대하여 생각하지 마라', 'Y', 'NORMAL', 3, NOW(), NOW());

INSERT INTO book(author, title, is_rental_available, book_status, category_id, created_at, updated_at)
VALUES('지승열', '실리콘밸리 리더십 쉽다', 'Y', 'NORMAL', 4, NOW(), NOW());
INSERT INTO book(author, title, is_rental_available, book_status, category_id, created_at, updated_at)
VALUES('지승열', '데이터분석을 위한 A 프로그래밍', 'Y',  'NORMAL', 4, NOW(), NOW());
INSERT INTO book(author, title, is_rental_available, book_status, category_id, created_at, updated_at)
VALUES('장동혁', '인공지능1-12', 'Y', 'NORMAL', 4, NOW(), NOW());
INSERT INTO book(author, title, is_rental_available, book_status, category_id, created_at, updated_at)
VALUES('위성원', '-1년차 게임 개발', 'Y', 'NORMAL', 4, NOW(), NOW());
INSERT INTO book(author, title, is_rental_available, book_status, category_id, created_at, updated_at)
VALUES('권태영', 'Skye가 알려주는 피부 채색의 비결', 'Y', 'NORMAL', 4, NOW(), NOW());

INSERT INTO book(author, title, is_rental_available, book_status, category_id, created_at, updated_at)
VALUES('장지명', '자연의 발전,', 'Y', 'NORMAL', 5, NOW(), NOW());
INSERT INTO book(author, title, is_rental_available, book_status, category_id, created_at, updated_at)
VALUES('이승열', '코스모스 필 무렵', 'Y', 'NORMAL', 5, NOW(), NOW());
