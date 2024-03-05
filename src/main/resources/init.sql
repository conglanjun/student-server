insert into user (id, name, phone, create_time, password, type, identity, role_id) values(1, '管理员', 19999999999, NOW(), 'e10adc3949ba59abbe56e057f20f883e', 'admin', '00001', 1);
--insert into notice (create_time, text) values(NOW(), '消防方面，请学生不得使用炊具煮饭炒菜，不得使用高功率电器，不乱拉电线，做到人离开后及时关闭电器，切断电源。积极做好防火安全，及时消除火灾隐患。');
--insert into notice (create_time, text) values(NOW(), '防盗方面，各位同学保管好自己的现金，电脑等贵重物品，离开宿舍及时关好门窗，将门锁好。宿舍员工严禁私自留宿外人，防止外人随意进入宿舍，若发现可疑人员，请速报告本楼管理员和保卫科。');
--insert into notice (create_time, text) values(NOW(), '严禁在宿舍内聚众赌博等违法活动，严禁高声喧哗，打闹，斗殴，以免妨碍他人休息。晚上及时就寝。');
--insert into notice (create_time, text) values(NOW(), '保持宿舍内，走廊，楼梯的清洁卫生，垃圾必须放在垃圾箱内。');

-- role
insert into role(id, name) values(1, 'admin');
insert into role(id, name) values(2, 'student');
insert into role(id, name) values(3, 'dormitory-manager');
insert into role(id, name) values(4, 'maintenance-manager');
insert into role(id, name) values(5, 'maintainer');