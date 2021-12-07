<h1>Alein Bartolome</h1>

<h2>Week 1</h2>

Responded to teammate and assisted in brainstorming project ideas. Led to a donations project.

<h2>Week 2</h2>

Drafted where the Functional Requirements, Technical Requirements, and Software Stack & Tools are from.

<h2>Week 3</h2>

Worked on finishing the last lab assignments.

<h2>Week 4</h2>
Met up with teammate and responded to any questions they had with their implementation

- Updated users, springcybersource, and html templates
- Worked on donationsapi
- Cleaned code to implement lombok and removed unnecessary code
- Attempted to implement rabbitmq for spring-payments

<h2>Commit History:</h2>

<h3>12/4/2021</h3>

[Commit 1]

[Commit 2]

[Commit 3]

[Commit 4]

<h4>Challenges</h4>
Discussed with my partner on any issues they had that they wanted me to investigate. I was told that they needed help on determining why “users” and “orders” were not executing properly. Therefore, I troubleshooted and referred to lab notes about spring-mysql and spring-rest. Documented my findings into NOTES.txt. Found out that “users” had their mapping annotations wrong and fixed it. Next, I performed tests to see if I can get a ping from Docker’s mysql from jumpbox and from the local computer. Then tested curl commands that were coded.

<h3>12/5/2021</h3>

[Commit 5]

[Commit 6]

[Commit 7]

[Commit 8]

[Commit 9]

[Commit 10]

<h4>Challenges</h4>
Had issues where the docker container could not connect to the mysql container. Needed to refer to Lab # 6 to remember that the containers were initially set up to run on “localhost.” Realizing that the data source connections are isolated “virtual machines,” I needed to change the connection to be MySQL’s Host name or IP from the container trying to access MySQL. I chose the former by creating a new network and having both containers in the same network.

<h3>12/6/2021</h3>

[Commit 11]

[Commit 12]

[Commit 13]

[Commit 14]

[Commit 15]

[Commit 16]

[Commit 17]

<h4>Challenges</h4>

- Anthony mentioned that he couldn’t get the register.html template to load. I investigated the issue and found three bugs.

- Anthony encountered an issue running PostMan with “users.” We originally thought that the method implemented for validation was causing an issue. Found out that spring-security was causing the problems so we had to remove it from build.gradle and the imports.

- Found out the Ingress apiVersion:  extensions/v1beta was deprecated. Solution was to switch to the new Ingress version using apiVersion:  networking.k8s.io/v1

- Encountered the same issues on performing Key Auth & K8S Secrets from Lab 8. 

[Commit 1]: https://github.com/nguyensjsu/fa21-172-end-game/commit/b0d6d4bb3138a19da1dae2dbc191d3a5d4348939
[Commit 2]: https://github.com/nguyensjsu/fa21-172-end-game/commit/70ff733885b40ea866624f1b0b8e2bbc0d3d8c38
[Commit 3]: https://github.com/nguyensjsu/fa21-172-end-game/commit/f25b4f1bc8151dd8f561111552957c6cfde9755c
[Commit 4]: https://github.com/nguyensjsu/fa21-172-end-game/commit/0ce2bc5d0b615b339256c31d7c4805bcc038cfdf
[Commit 5]: https://github.com/nguyensjsu/fa21-172-end-game/commit/e3e13a485736fa41003420fac7537c570d4c95b4
[Commit 6]: https://github.com/nguyensjsu/fa21-172-end-game/commit/ec84938cedeea9ac6f02d6e37e12c27af6caf312
[Commit 7]: https://github.com/nguyensjsu/fa21-172-end-game/commit/77010e23c33a08c445e38d38d831b12e9153ad95
[Commit 8]: https://github.com/nguyensjsu/fa21-172-end-game/commit/09d1518d26427558ea7e7932f81dd14403611086
[Commit 9]: https://github.com/nguyensjsu/fa21-172-end-game/commit/3cc1fd8a0b3f28335cb9d4d8b8d8cead4b379876
[Commit 10]: https://github.com/nguyensjsu/fa21-172-end-game/commit/29bb01dab3c1dd8362ac7f69476752be1ba168ee
[Commit 11]: https://github.com/nguyensjsu/fa21-172-end-game/commit/8c49162413aa558ed7636021c42911d1f8ce9f36
[Commit 12]: https://github.com/nguyensjsu/fa21-172-end-game/commit/6b911defbf045cccccc0481e7b4dfaf49ebc848a
[Commit 13]: https://github.com/nguyensjsu/fa21-172-end-game/commit/5f91a27df107752134e812bd253a2e3949085bf4
[Commit 14]: https://github.com/nguyensjsu/fa21-172-end-game/commit/019329a6c124a6d97d46db8be42b1760059fdb8a
[Commit 15]: https://github.com/nguyensjsu/fa21-172-end-game/commit/2999a5177210d1bae3689bd0737c96975a4995ef
[Commit 16]: https://github.com/nguyensjsu/fa21-172-end-game/commit/f0e966266332643606d66edf266cffbc8e11cd45
[Commit 17]: https://github.com/nguyensjsu/fa21-172-end-game
