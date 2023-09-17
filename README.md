# socialplatformT

可至以下連結查看網頁內容介紹
https://drive.google.com/file/d/1NrRUCvCBFtTV_Vi74azRZL2T-PkoA6qH/view?usp=drive_link

如何使用：
1. 下載zip檔案解壓縮
2. 於資料庫中執行 DML, DDL (放在 src/main/resources/DB
3. 於src/main/resources/application.properties 中更新資料庫帳密以及URL
4. JDK 編譯及執行專案
5. 入口網頁 article.html (放在 src/main/resources/static

實現功能：
1. 註冊 register.html
   - 驗證輸入是否符合格式(正規表達式)，若不符合，紅字提示會一直顯示直到符合規則。
   - 點選註冊
     以手機為帳號，後端驗證，若有註冊過，會提示已經有註冊過無法註冊
     無註冊過的話，將資料送入資料庫
   - 註冊後轉跳至登入畫面
     
2. 登入 login.html
   - 驗證輸入是否符合格式，若不符合，紅字提示會一直顯示直到符合規則。
   - 點選登入 (後端由帳密驗證，
   - 登入成功後轉跳至文章平台
     
3. 文章 article.html
   - 登入後才可以進入發布文章的頁面，並且可以編輯自己發的文 (編輯按鈕只會在登入時顯示)
   - 列出所有文章列表
     
4. 評論系統
   - 評論文章 (登入後才可以針對每個文章做評論，評論按鈕只會在登入時顯示
     備註: 若已經點選了查看評論，同時評論文章的話，需要再點一次查看評論，剛剛的評論才會刷新(待修改的功能）
     
   - 查看評論 (訪客與會員都可以查看評論
     沒有評論的話會顯示無評論，有評論的話則是列出所有評論

實現技術
1. 前端: Vue.js (引入cdn), boostrap 
2. 環境: Spring boot ( Maven
3. 架構: web MVC (RESTful style
   實作Transaction、使用 MySQL 關聯式資料庫
4. Stored Procedure 及參數化查詢，防止SQL injection
5. Spring 提供的 HtmlUtils.htmlEscape 轉義內容，防止XSS攻擊
6. 密碼經由 Spring Security crypto BCryptPasswordEncoder 加鹽哈希處理後存入資料庫，登入時驗證

