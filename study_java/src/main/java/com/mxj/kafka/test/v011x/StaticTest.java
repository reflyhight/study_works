

package com.mxj.kafka.test.v011x;

import java.util.Arrays;
import java.util.List;

import org.apache.kafka.common.config.ConfigException;
import org.apache.kafka.common.config.ConfigDef.ValidString;
import org.apache.kafka.common.config.ConfigDef.Validator;
import org.apache.kafka.common.utils.Utils;

/**
 * 
 * @author Rob Jiang
 * @date 2017年10月19日
 * @email jh624haima@126.com
 * @company blog.mxjhaima.com
 */
public class StaticTest {
	
	public static class ValidString implements Validator {
        List<String> validStrings;

        private ValidString(List<String> validStrings) {
            this.validStrings = validStrings;
        }

        public static ValidString in(String... validStrings) {
            return new ValidString(Arrays.asList(validStrings));
        }

        @Override
        public void ensureValid(String name, Object o) {
            String s = (String) o;
            if (!validStrings.contains(s)) {
                throw new ConfigException(name, o, "String must be one of: " + Utils.join(validStrings, ", "));
            }

        }

        public String toString() {
            return "[" + Utils.join(validStrings, ", ") + "]";
        }
    }
	
	static{
//		ValidString.in(validStrings);
	}
	
	public static void main(String[] args) {
		
	}

}
