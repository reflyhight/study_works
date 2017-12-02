package com.mxj.jsoup;

public class Selector {

	
	public static void main(String[] args) {
		
		//		Selector overview	普通选择器
				
		//		tagname: find elements by tag, e.g. a	#标签，		
		//		ns|tag: find elements by tag in a namespace, e.g. fb|name finds <fb:name> elements	#标签ns:tag
		//		#id: find elements by ID, e.g. #logo	#ID选择器
		//		.class: find elements by class name, e.g. .masthead	#class选择器
		//		[attribute]: elements with attribute, e.g. [href]	#属性选择器
		//		#以attr开头的属性的元素
		//		[^attr]: elements with an attribute name prefix, e.g. [^data-] finds elements with HTML5 dataset attributes	
		//		#属性attr值为	value的元素
		//		[attr=value]: elements with attribute value, e.g. [width=500] (also quotable, like [data-name='launch sequence'])
		//		#attr属性值以value开头，attr以value结尾，attr值包含value的
		//		[attr^=value], [attr$=value], [attr*=value]: elements with attributes that start with, end with, or contain the value, e.g. [href*=/path/]
		//		#attr属性值正则匹配regex的
		//		[attr~=regex]: elements with attribute values that match the regular expression; e.g. img[src~=(?i)\.(png|jpe?g)]
		//		*: all elements, e.g. *
		
		
		
		//		Selector combinations	组合选择器
		
		//		#且组合，
		//		el#id: elements with ID, e.g. div#logo	#id为logo的div
		//		el.class: elements with class, e.g. div.masthead	#class为masthead的div
		//		el[attr]: elements with attribute, e.g. a[href]	#有href属性的a
		//		Any combination, e.g. a[href].highlight
		
		//		#继承关系,按照	ancestor child顺序找
		//		ancestor child: child elements that descend from ancestor, e.g. .body p finds p elements anywhere under a block with class "body"
		//		#子父级关系	
		//		parent > child: child elements that descend directly from parent, e.g. div.content > p finds p elements; and body > * finds the direct children of the body tag
		
		//		#兄弟选择器
		//		#A+B:	B紧跟着A之后，则选择B	（AB之间没有其他元素）
		//		siblingA + siblingB: finds sibling B element immediately preceded by sibling A, e.g. div.head + div
		//		#兄弟元素中，	B在A的后面，则选B，（AB之间可以有其他元素）
		//		siblingA ~ siblingX: finds sibling X element preceded by sibling A, e.g. h1 ~ p
		
		//		#或组合
		//		el, el, el: group multiple selectors, find unique elements that match any of the selectors; e.g. div.masthead, div.logo
		
		
		//		Pseudo selectors	伪选择器	
		
		//		#在兄弟元素中的排序
		//		:lt(n): find elements whose sibling index (i.e. its position in the DOM tree relative to its parent) is less than n; e.g. td:lt(3)
		//		:gt(n): find elements whose sibling index is greater than n; e.g. div p:gt(2)
		//		:eq(n): find elements whose sibling index is equal to n; e.g. form input:eq(1)
		
		//		#div:has(p)	内部包含P的div
		//		:has(selector): find elements that contain elements matching the selector; e.g. div:has(p)
		//		#非选择器
		//		:not(selector): find elements that do not match the selector; e.g. div:not(.logo)
		//		#内容包含
		//		:contains(text): find elements that contain the given text. The search is case-insensitive; e.g. p:contains(jsoup)
		//		#内容包含，直接包含关系
		//		:containsOwn(text): find elements that directly contain the given text
		//		#内容匹配正则
		//		:matches(regex): find elements whose text matches the specified regular expression; e.g. div:matches((?i)login)
		//		#内容匹配，直接关系，不通过子元素匹配
		//		:matchesOwn(regex): find elements whose own text matches the specified regular expression
		//		#注意上面的索引基于0为开始索引，第一个元素为0
		//		Note that the above indexed pseudo-selectors are 0-based, that is, the first element is at index 0, the second at 1, etc
	}
}
