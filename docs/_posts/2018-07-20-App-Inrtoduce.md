---
layout: post
title:  "应用介绍"
date:   2018-07-19 15:50:00
categories: jekyll
---

<!-- more -->

## 开发初衷
基于Xposed框架的patch特性，可以很方便的用作Android Framework开发调试，
因此一开始X-APM仅仅是用来试水Xposed框架的一个小小的测试，因此我们可以看到应用的包名是以test结尾的，
后来因为改了包名会导致应用需要重新安装，保留了该包名（某个国产ROM，如果包名是test结尾，会被认为是测试包，Release版本上无法安装）。

## 功能来源
大部分功能的idea来自华为的EMUI6中集成的手机管家，也有一部分来自酷友的建议。这些功能都是偏向设备于应用的管理与优化方面，因此美化相关的功能是不存在的。

## 工作原理
X-APM整体架构分两层，分别是APP层，FW（Framework）层。

![Arch-Android-X-APM](/X-APM/assets/post-app-introduce/X-APM-Arch-Android.jpg)

* FW层运行于system_server进程，伴随着AMS（ActivityManagerService，系统中的一个管理服务）的启动而启动，
只要负责核心管理逻辑，拥有至高无上的权限，用户层APP无法干扰。
* APP层仅仅是一个普通的应用级别，只负责为用户提供UI交互，可与FW层进行IPC，完成用户的配置。

因此，X-APM应用在Android初始化的时候就完成了部署，设备启动完成后，用户无需进行后台的维护，用户的感受就是无需后台。
