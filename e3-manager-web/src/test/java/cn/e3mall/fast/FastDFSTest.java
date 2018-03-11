package cn.e3mall.fast;

import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient;
import org.csource.fastdfs.StorageServer;
import org.csource.fastdfs.TrackerClient;
import org.csource.fastdfs.TrackerServer;
import org.junit.Test;

import cn.e3mall.common.utils.FastDFSClient;

public class FastDFSTest {

	@Test
	public void testUpload() throws Exception{
		//添加依赖，创建一个配置文件，包含tracker服务器的地址
		//使用全局对象加载配置文件
		ClientGlobal.init("E:/projectmodel/e3-manager-web/src/main/resources/conf/client.conf");
		//创建trackerClient对象
		TrackerClient trackerClient = new TrackerClient();
		//通过trackerClient获得trackerServer对象
		TrackerServer trackerServer = trackerClient.getConnection();
		//创建一个StorageServer的引用，可以是null。
		StorageServer storageServer = null;
		//创建一个StorageClient，参数需要trackerServer和StorageServer
		StorageClient storageClient = new StorageClient(trackerServer, storageServer);
		//使用StorageClient上传文件
		String[] strings = storageClient.upload_file("E:/a.png", "png",null);
		for (String string : strings) {
			System.out.println(string);
		}
	}
	
	@Test
	public void testFastDFSClient() throws Exception{
		FastDFSClient fastDFSClient = new FastDFSClient("E:/projectmodel/e3-manager-web/src/main/resources/conf/client.conf");
		String string = fastDFSClient.uploadFile("E:/a.png");
		System.out.println(string);
	}
}
