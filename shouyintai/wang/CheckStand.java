package com.wang;

import java.io.FileNotFoundException;
import java.util.Scanner;

public class CheckStand  {

        private static Scanner scanner = new Scanner(System.in);

        //商品中心管理
        private static GoodsCenter goodsCenter = new SimpleGoodsCenter();

        //订单中心管理
        private static OrderCenter orderCenter = new SimpleOrderCenter(goodsCenter);

        private static Integer orderId = 0;


        public static void helpInfo() {
            System.out.println("*************** 欢迎来到seal简易收银台 *****************");
            System.out.println("       [U] 使用 [S] 设置 [P] 保存 [A] 关于 [Q] 退出");
            System.out.println("       输入:  U S P A Q 进入操作                    ");
            System.out.println("*************************************************");
        }

        public static void quit() {
            System.out.println("*************************************************");
            System.out.println("          欢迎使用seal收银台系统，下次再见          ");
            System.out.println("*************************************************");
            System.exit(0);
        }

        public static void about() {
            System.out.println("******************** 关于 ***********************");
            System.out.println("        名称：简易收银台                          ");
            System.out.println("        功能：基于字符界面的收银台操作系统          ");
            System.out.println("        意见反馈：WangHaiBao9712@163.com            ");
            System.out.println("*************************************************");
        }

        public static void usageInfo() {
            System.out.println("******************* 买单功能 ********************");
            System.out.println("    [A] 添加商品 [D] 取消商品 [L] 浏览商品        ");
            System.out.println("    [S] 查看订单 [R] 返回上级        ");
            System.out.println("       输入:  A D L S R 进入操作                  ");
            System.out.println("*************************************************");
        }

        public static void settingInfo() {
            System.out.println("******************* 设置功能 ********************");
            System.out.println("    [A] 商品上架 [D] 商品下架 [U] 商品修改        ");
            System.out.println("    [S] 查看商品 [L] 浏览订单 [R] 返回上级        ");
            System.out.println("       输入:  A D U S L R 进入操作                ");
            System.out.println("*************************************************");
        }

        public static void usage() {
            //创建订单，并且添加到订单管理中心
            Order order = new Order(String.valueOf(++orderId));
            orderCenter.addOrder(order);
            usageInfo();
            System.out.println(orderCenter.orderTable(order.getOrderld()));
            while (true) {
                String line = scanner.nextLine();
                switch (line.trim().toUpperCase()) {
                    case "S": {
                        System.out.println(orderCenter.orderTable(order.getOrderld()));
                        break;
                    }
                    case "A": {
                        System.out.println("请输入下单信息[编号 数量]（如下格式：1  2 ）:");
                        String value = scanner.nextLine();
                        String[] infoArray = value.split(" ");
                        if (infoArray.length == 2) {
                            Goods goods = goodsCenter.getGoods(infoArray[0]);
                            if (goods != null) {
                                order.add(infoArray[0], Integer.parseInt(infoArray[1]));

                                System.out.println(orderCenter.orderTable(order.getOrderld()));
                                break;
                            }
                        }
                        System.out.println("请按照格式要求输入信息");
                        break;
                    }
                    case "D": {
                        System.out.println("请输入取消信息[编号 数量]（如下格式：1  2 ）:");
                        String value = scanner.nextLine();
                        String[] infoArray = value.split(" ");
                        if (infoArray.length == 2) {
                            Goods goods = goodsCenter.getGoods(infoArray[0]);
                            if (goods != null) {
                                order.cancel(infoArray[0], Integer.parseInt(infoArray[1]));
                                System.out.println(orderCenter.orderTable(order.getOrderld()));
                                break;
                            }
                        }
                        System.out.println("请按照格式要求输入信息");
                        break;
                    }
                    case "L": {
                        System.out.println(goodsCenter.listGoods());
                        break;
                    }
                    case "R": {
                        return;
                    }
                    default: {
                        usageInfo();
                    }
                }
            }
        }

        public static void setting() {
            settingInfo();
            while (true) {
                String line = scanner.nextLine();
                //不区分大小写
                switch (line.toUpperCase()) {
                    case "A": {
                        //默认连续添加，当为false时不再继续
                        do {
                            System.out.println("请输入上架商品信息（如下格式：1 华为 9999.9）:");
                            Goods goods = readGoods();
                            if (goods == null) {
                                System.out.println("!请按照格式要求输入信息");
                                break;
                            }
                            if (goodsCenter.isExiistGoods(goods.getId())) {
                                System.out.println("!上架商品已经存在，注意编号不能重复");
                            } else {
                                goodsCenter.addGoods(goods);
                            }
                            System.out.println(goodsCenter.listGoods());
                            System.out.println("是否继续添加货物，不继续请按0，继续请按任意键");
                        }while(read());
                        break;
                    }
                    case "D": {
                        do {
                            System.out.println("请输入下架商品信息编号（如下格式：1 ）:");
                            Goods goods = readGoods();
                            if (goods == null) {
                                System.out.println("请按照格式要求输入信息");
                                break;
                            }
                            if (goodsCenter.isExiistGoods(goods.getId())) {
                                goodsCenter.removeGoods(goods.getId());
                            } else {
                                System.out.println("请输入存在的商品");
                            }
                            System.out.println("是否继续下架货物，不继续请按0，继续请按任意键");
                        }while(read());
                        System.out.println(goodsCenter.listGoods());
                        break;
                    }
                    case "U": {
                        do {
                            System.out.println("请输入修改商品信息（如下格式：1 餐巾纸 1.4 ）");
                            Goods goods = readGoods();
                            if (goods == null) {
                                System.out.println("请按照格式要求输入信息");
                                break;
                            }

                            if (goodsCenter.isExiistGoods(goods.getId())) {
                                goodsCenter.updataGodds(goods);
                            } else {
                                System.out.println("请输入存在的商品");
                            }
                            System.out.println(goodsCenter.listGoods());
                            System.out.println("是否继续修改货物，不继续请按0，继续请按任意键");
                        }while(read());
                        break;
                    }
                    case "S": {
                        System.out.println(goodsCenter.listGoods());
                        break;
                    }
                    case "L": {
                        System.out.println(orderCenter.ordersTable());
                        break;
                    }
                    case "R": {
                        return;
                    }
                    default: {
                        settingInfo();
                    }
                }
            }
        }

        public static Goods readGoods() {
            String value = scanner.nextLine();
            String[] infoArray = value.split(" ");
            if (infoArray.length == 3 || infoArray.length == 1) {
                if (infoArray.length == 3) {
                    Goods goods = new Goods(infoArray[0], infoArray[1], Double.parseDouble(infoArray[2]));
                    return goods;
                }
                Goods goods = new Goods(infoArray[0], "", 0.0D);
                return goods;
            }
            return null;
        }
        public static boolean read(){
            String value = scanner.nextLine();
            String[] infoArray = value.split(" ");
            if(infoArray[0].equals("0")) {
                return false;
            }
            return true;
        }

        public static void main(String[] args) throws FileNotFoundException {
            helpInfo();
            //初始化加载文件信息
            //goodsCenter.load();
            //orderCenter.loadOrder();
            goodsCenter.load2();
            orderCenter.loadOrder2();
            while (true) {
                String line = scanner.nextLine();
                switch (line.trim().toUpperCase()) {
                    case "U":
                        usage();
                        helpInfo();
                        break;
                    case "S":
                        setting();
                        helpInfo();
                        break;
                    case "A":
                        about();
                        break;
                    case "P":
                        //商品信息保存
                        //goodsCenter.store();
                        goodsCenter.store2();
                        //订单信息保存
                        //orderCenter.storeOreder();
                        goodsCenter.load2();
                        break;
                    case "Q":
                        quit();
                        break;
                    default:
                        helpInfo();
                }
            }
        }
    }


