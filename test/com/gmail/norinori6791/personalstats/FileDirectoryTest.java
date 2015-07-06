package com.gmail.norinori6791.personalstats;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class FileDirectoryTest {
	
	public static class コンストラクタ取得{
		
		FileDirectory fd;

		@Before
		public void setUp() throws Exception {
			fd = new FileDirectory();
		}
		@Test
		public void 無指定コンストラクタ取得() {
			assertThat(fd.getSelectedFile(), is("/storage/sdcard0/"));
		}
	}

}
