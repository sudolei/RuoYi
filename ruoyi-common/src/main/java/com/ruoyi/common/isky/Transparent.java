package com.ruoyi.common.isky;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Administrator
 * @date Apr 26, 2016
 * @time 4:57:19 PM
 * @typeName Transparent
 */
public class Transparent {
	public static void main(String[] args) throws IOException {
		String filePath = "F:\\2016印赛格\\明信片测试\\测试B (1).png";
		if (filePath == null || filePath.equals("")) {
			System.out.println("请输入图片地址！");
			return;
		}
		File file = new File(filePath);
		if (!file.exists()) {
			System.out.println("文件不存在！");
			return;
		}
		BufferedImage bi = (BufferedImage) ImageIO.read(new File(filePath));
		int width = bi.getWidth();
		int height = bi.getHeight();
		ArrayList<Block> block = new ArrayList<Block>();
		int count = 0;
		int updip = 0;
		int rightdip = 0;
		int downdip = 0;
		int leftdip = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {// 行扫描
				int dip = bi.getRGB(j, i);

				if (dip >> 24 == 0) {
					if (i != 0){
                        updip = bi.getRGB(j, i - 1);
                    }

					if (i != height - 1){
                        downdip = bi.getRGB(j, i + 1);
                    }

					if (j != 0){
                        leftdip = bi.getRGB(j - 1, i);
                    }

					if (j != width - 1){
                        rightdip = bi.getRGB(j + 1, i);
                    }
					if ((count == 0 && i == 0)
							|| (j == 0 && (updip >> 24) != 0 && (downdip >> 24 == 0) && (rightdip >> 24 == 0))) {
						Block b = new Block();
						b.xstart = j;
						b.ystart = i;
						count++;
						block.add(b);
					} else if (i == height - 1
							|| (j == width - 1 && (updip >> 24) == 0 && (downdip >> 24 != 0) && (leftdip >> 24 == 0))) {
						if (count > 0) {
							Block b = block.get(count - 1);
							b.yend = i;
							b.xend = j;
						}
					}
					if ((updip >> 24) != 0 && (downdip >> 24 == 0) && (leftdip >> 24 != 0) && (rightdip >> 24 == 0)) {
						if (count > 0) {
							int k = 0;
							for (k = 0; k < block.size(); k++) {
								Block ba = block.get(k);
								if (Math.abs(ba.xstart - j) < 10 && Math.abs(ba.ystart - i) < 10) {
									break;
								}
							}
							if (k == block.size()) {
								Block b = new Block();
								b.xstart = j;
								b.ystart = i;
								count++;
								int m = 0;
								for (m = 0; m < block.size(); m++) {
									Block b1 = block.get(m);
									if (b.xstart >= b1.xstart)
										continue;
									else {
										break;
									}
								}
								block.add(m, b);
							}
						} else {
							Block b = new Block();
							b.xstart = j;
							b.ystart = i;
							count++;
							block.add(b);
						}
					} else if ((updip >> 24) == 0 && (downdip >> 24 != 0) && (leftdip >> 24 == 0)
							&& (rightdip >> 24 != 0)) {
						int k = 0;
						for (k = block.size() - 1; k >= 0; k--) {
							Block b = block.get(k);
							if ((i - b.yend) > 10 && (i - b.ystart) > 10 && (j - b.xend) > 10 && (j - b.xstart) > 10
									&& b.xend == 0 && b.yend == 0) {
								b.yend = i;
								b.xend = j;
								break;
							}

						}
					}
				}

			}

		}

		System.out.print("共有方块数" + count + "个：\n");
		for (int k = 0; k < block.size(); k++) {
			Block b = (Block) block.get(k);
			// System.out.println(b.xstart+","+b.ystart +" "+b.xend+","+b.yend);
			System.out.print(String.valueOf(k + 1) + ".宽" + String.valueOf(b.xend - b.xstart + 1) + "像素" + "，长"
					+ String.valueOf(b.yend - b.ystart + 1) + "像素，中心点："
					+ String.valueOf((b.xend - b.xstart + 1) / 2 + b.xstart) + ","
					+ String.valueOf((b.yend - b.ystart + 1) / 2 + b.ystart) + "\n");
		}
	}

	public static ArrayList<Block> getPhotoProperty(String filePath) throws IOException {

		if (filePath == null || filePath.equals("")) {
			System.out.println("请输入图片地址！");
			return null;
		}
		File file = new File(filePath);
		if (!file.exists()) {
			System.out.println("文件不存在！");
			return null;
		}
		BufferedImage bi = (BufferedImage) ImageIO.read(new File(filePath));
		int width = bi.getWidth();
		int height = bi.getHeight();
		ArrayList<Block> block = new ArrayList<Block>();
		int count = 0;
		int updip = 0;
		int rightdip = 0;
		int downdip = 0;
		int leftdip = 0;
		for (int i = 0; i < height; i++) {
			for (int j = 0; j < width; j++) {// 行扫描
				int dip = bi.getRGB(j, i);

				if (dip >> 24 == 0) {
					if (i != 0){
                        updip = bi.getRGB(j, i - 1);
                    }
					if (i != height - 1){
                        downdip = bi.getRGB(j, i + 1);
                    }
					if (j != 0){
                        leftdip = bi.getRGB(j - 1, i);
                    }
					if (j != width - 1){
                        rightdip = bi.getRGB(j + 1, i);
                    }
					if ((count == 0 && i == 0) || (j == 0 && (updip >> 24) != 0 && (downdip >> 24 == 0) && (rightdip >> 24 == 0))) {
						Block b = new Block();
						b.xstart = j;
						b.ystart = i;
						count++;
						block.add(b);
					} else if (i == height - 1
							|| (j == width - 1 && (updip >> 24) == 0 && (downdip >> 24 != 0) && (leftdip >> 24 == 0))) {
						if (count > 0) {
							Block b = block.get(count - 1);
							b.yend = i;
							b.xend = j;
						}
					}
					if ((updip >> 24) != 0 && (downdip >> 24 == 0) && (leftdip >> 24 != 0) && (rightdip >> 24 == 0)) {
						if (count > 0) {
							int k = 0;
							for (k = 0; k < block.size(); k++) {
								Block ba = block.get(k);
								if (Math.abs(ba.xstart - j) < 10 && Math.abs(ba.ystart - i) < 10) {
									break;
								}
							}
							if (k == block.size()) {
								Block b = new Block();
								b.xstart = j;
								b.ystart = i;
								count++;
								int m = 0;
								for (m = 0; m < block.size(); m++) {
									Block b1 = block.get(m);
									if (b.xstart >= b1.xstart)
										continue;
									else {
										break;
									}
								}
								block.add(m, b);
							}
						} else {
							Block b = new Block();
							b.xstart = j;
							b.ystart = i;
							count++;
							block.add(b);
						}
					} else if ((updip >> 24) == 0 && (downdip >> 24 != 0) && (leftdip >> 24 == 0)
							&& (rightdip >> 24 != 0)) {
						int k = 0;
						for (k = block.size() - 1; k >= 0; k--) {
							Block b = block.get(k);
							if ((i - b.yend) > 10 && (i - b.ystart) > 10 && (j - b.xend) > 10 && (j - b.xstart) > 10
									&& b.xend == 0 && b.yend == 0) {
								b.yend = i;
								b.xend = j;
								break;
							}

						}
					}
				}

			}

		}
		return block;

		// System.out.print("共有方块数" + count + "个：\n");
		// for (int k = 0; k < block.size(); k++) {
		// Block b = (Block) block.get(k);
		// System.out.println(b.xstart+","+b.ystart +" "+b.xend+","+b.yend);
		// System.out.print(String.valueOf(k + 1) + ".长"
		// + String.valueOf(b.xend - b.xstart + 1) + "像素" + "，宽"
		// + String.valueOf(b.yend - b.ystart + 1) + "像素，中心点："
		// + String.valueOf((b.xend - b.xstart + 1) / 2 + b.xstart)
		// + ","
		// + String.valueOf((b.yend - b.ystart + 1) / 2 + b.ystart)
		// + "\n");
		// }

	}
}
