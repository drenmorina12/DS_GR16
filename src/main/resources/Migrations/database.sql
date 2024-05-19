use ds_gr16;


CREATE TABLE tblAdmin (
                          adminId INT AUTO_INCREMENT PRIMARY KEY,
                          email VARCHAR(50) NOT NULL,
                          emri VARCHAR(50)  NOT NULL,
                          mbiemri VARCHAR(50) NOT NULL,
                          salt VARCHAR(100) NOT NULL,
                          passwordHash VARCHAR(400) NOT NULL
);


CREATE TABLE tblUser (
                         userId INT AUTO_INCREMENT PRIMARY KEY,
                         email VARCHAR(50) NOT NULL,
                         emri VARCHAR(50),
                         mbiemri VARCHAR(50),
                         salt VARCHAR(100) NOT NULL,
                         passwordHash VARCHAR(400) NOT NULL
);

-- password Drenmorina
insert into tblAdmin (email,emri,mbiemri,salt,passwordHash)
values ("dren@admin.com", "dren", "morina", "KGO4aU2gLkBe6PM+0LnxWsJcG80VfXviSyqSAmT+PcI=", "4b474f34615532674c6b426536504d2b304c6e7857734a63473830566658766953797153416d542b5063493d4a6980fb30d54ffa005fab461516bdb490101f0c256dce674e6f80b73ee7aa3d");

select * from tbladmin;
-- delete from tblAdmin admin where adminId = 2;

-- password Drenmorina
select * from tblUser;
insert into tblUser (email,emri,mbiemri,salt,passwordHash)
values ("filan@gmail.com", "filan", "fisteku", "KGO4aU2gLkBe6PM+0LnxWsJcG80VfXviSyqSAmT+PcI=", "4b474f34615532674c6b426536504d2b304c6e7857734a63473830566658766953797153416d542b5063493d4a6980fb30d54ffa005fab461516bdb490101f0c256dce674e6f80b73ee7aa3d");

