CREATE TABLE IF NOT EXISTS member(
    id                          BIGINT              NOT NULL            AUTO_INCREMENT PRIMARY KEY,
    username                    VARCHAR(15)         NOT NULL            COMMENT '아이디(계정)',
    password                    VARCHAR(255)        NOT NULL,
    nickname                    VARCHAR(30)         NOT NULL,
    email                       VARCHAR(255)        NOT NULL,
    reg_date                    DATETIME            NOT NULL            DEFAULT now(),
    -- PENDING, ACTIVE, SUSPENDED(BLOCKED), SLEEP, LOCKED(PROTECTED), REMOVED
    status                      VARCHAR(30)         NOT NULL            DEFAULT 'PENDING',
    role_name                    VARCHAR(20)         NOT NULL            default 'USER_ROLE',

    CONSTRAINT uq_member_username UNIQUE (username),
    CONSTRAINT uq_member_nickname UNIQUE (nickname),
    CONSTRAINT uq_member_email UNIQUE (email)
);

-- DB가 개발을 방해하면 안 되게
--  -> 느슨한 제약조건
--  -> VARCHAR는 특히 크기를 작게 줘서 이점이 없음. email 100     101

-- 로그인 오래 걸리면 좋음. 무작위 대입으로부터 더 안전해짐.
-- 인증 오래 걸리게(1초 내외 <- UX 반영하면.)
-- 인가 짧게(API 써도 되는 사람인지 아닌지 확인하는 작업) -> 모든 기능의 지연을 뜻하니까.

-- 회원 쪽 -> 민감
-- -> 제약조건 줘도 됨.

-- 그 외: 제약조건을 DB단에 걸어 놓으면 개발할 때마다 서로 소통 비용 + 작업량 + 요즘은 소스코드 보호가 됨(메인 브랜치에 신입이 혼자 코드 넣을 수조차 없음. 시스템적으로 막힘.)
-- NOT NULL, FOREIGN KEY(NEVER) -> 지양
-- UNIQUE -> 그나마 괜찮.
