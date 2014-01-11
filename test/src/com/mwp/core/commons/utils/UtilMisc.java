
package com.mwp.core.commons.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

/**
 * UtilMisc - Misc Utility Functions
 */
public final class UtilMisc
{
    public static void main(String[] args) throws IOException
    {
        // File f = new File("E:/wangwen/构架文档/2.架构设计/MAR2平台/ui/拓扑图.bmp");
        File f = new File("E:/wangwen/构架文档/2.架构设计/MAR2平台/ui/UI制作规范.docx");
        System.out.println(getWidthAndHeight(FileUtils.openInputStream(f))[0]);
    }

    public static int[] getWidthAndHeight(InputStream input) throws IOException
    {
        int[] result = new int[2];
        BufferedImage buff = ImageIO.read(input);
        result[0] = buff.getWidth();
        result[1] = buff.getHeight();
        return result;
    }

    public static int[] getWidthAndHeight(File file) throws IOException
    {
        FileInputStream input = null;
        try
        {
            input = FileUtils.openInputStream(file);
            return getWidthAndHeight(input);
        }
        finally
        {
            IOUtils.closeQuietly(input);
        }

    }

    public static String getFormatName(Object o)
    {
        try
        {
            // Create an image input stream on the image
            ImageInputStream iis = ImageIO.createImageInputStream(o);
            // Find all image readers that recognize the image format
            Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);
            if (!iter.hasNext())
            {
                // No readers found
                return null;
            }

            // Use the first reader
            ImageReader reader = iter.next();
            // Close stream
            iis.close();
            // System.out.println(reader.getWidth(0));

            // Return the format name
            return reader.getFormatName();
        }
        catch (IOException e)
        {
            //
        }
        // The image could not be read
        return null;
    }

    public static final String UNICODE = "unicode";

    /**
     * Get an iterator from a collection, returning null if collection is null
     * @param col
     *        The collection to be turned in to an iterator
     * @return The resulting Iterator
     */

    public static <T> Iterator<T> toIterator(Collection<T> col)
    {
        if (col == null)
            return null;
        else
            return col.iterator();
    }

    /**
     * Create a map from passed nameX, valueX parameters
     * @return The resulting Map
     */
    public static Map<String, Object> toMap(String name1, Object value1)
    {
        return new UtilMisc.SimpleMap(name1, value1);
    }

    /**
     * Create a map from passed nameX, valueX parameters
     * @return The resulting Map
     */
    public static Map<String, Object> toMap(String name1, Object value1, String name2, Object value2)
    {
        return new UtilMisc.SimpleMap(name1, value1, name2, value2);
    }

    /**
     * Create a map from passed nameX, valueX parameters
     * @return The resulting Map
     */
    public static Map<String, Object> toMap(String name1, Object value1, String name2, Object value2, String name3, Object value3)
    {
        return new UtilMisc.SimpleMap(name1, value1, name2, value2, name3, value3);

    }

    /**
     * Create a map from passed nameX, valueX parameters
     * @return The resulting Map
     */
    public static Map<String, Object> toMap(String name1, Object value1, String name2, Object value2, String name3, Object value3,
            String name4, Object value4)
    {
        return new UtilMisc.SimpleMap(name1, value1, name2, value2, name3, value3, name4, value4);
    }

    /**
     * Create a map from passed nameX, valueX parameters
     * @return The resulting Map
     */
    public static Map<String, Object> toMap(String name1, Object value1, String name2, Object value2, String name3, Object value3,
            String name4, Object value4, String name5, Object value5)
    {
        Map<String, Object> fields = new HashMap<String, Object>();

        fields.put(name1, value1);
        fields.put(name2, value2);
        fields.put(name3, value3);
        fields.put(name4, value4);
        fields.put(name5, value5);
        return fields;
    }

    /**
     * Create a map from passed nameX, valueX parameters
     * @return The resulting Map
     */
    public static Map<String, Object> toMap(String name1, Object value1, String name2, Object value2, String name3, Object value3,
            String name4, Object value4, String name5, Object value5, String name6, Object value6)
    {
        Map<String, Object> fields = new HashMap<String, Object>();

        fields.put(name1, value1);
        fields.put(name2, value2);
        fields.put(name3, value3);
        fields.put(name4, value4);
        fields.put(name5, value5);
        fields.put(name6, value6);
        return fields;
    }

    /**
     * Create a map from passed nameX, valueX parameters
     * @return The resulting Map
     */
    public static Map<String, Object> toMap(String name1, Object value1, String name2, Object value2, String name3, Object value3,
            String name4, Object value4, String name5, Object value5, String name6, Object value6, String name7, Object value7)
    {
        Map<String, Object> fields = new HashMap<String, Object>();

        fields.put(name1, value1);
        fields.put(name2, value2);
        fields.put(name3, value3);
        fields.put(name4, value4);
        fields.put(name5, value5);
        fields.put(name6, value6);
        fields.put(name7, value7);
        return fields;
    }

    /**
     * Create a map from passed nameX, valueX parameters
     * @return The resulting Map
     */
    public static Map<Object, Object> toMap(Object[] data)
    {
        if (data == null)
        {
            return null;
        }
        if (data.length % 2 == 1)
        {
            throw new IllegalArgumentException("You must pass an even sized array to the toMap method");
        }
        Map<Object, Object> map = new HashMap<Object, Object>();
        for (int i = 0; i < data.length;)
        {
            map.put(data[i++], data[i++]);
        }
        return map;
    }

    public static String printMap(Map<? extends Object, ? extends Object> theMap)
    {
        StringBuffer theBuf = new StringBuffer();
        Iterator<?> entryIter = theMap.entrySet().iterator();
        while (entryIter.hasNext())
        {
            Map.Entry<?, ?> entry = (Entry<?, ?>) entryIter.next();
            theBuf.append(entry.getKey());
            theBuf.append(" --> ");
            theBuf.append(entry.getValue());
            theBuf.append("\n");
        }
        return theBuf.toString();
    }

    public static <T> T removeFirst(List<T> lst)
    {
        return lst.remove(0);
    }

    /**
     * Create a list from passed objX parameters
     * @return The resulting List
     */
    public static <T> List<T> toList(T obj1)
    {
        List<T> list = Collections.singletonList(obj1);
        return list;
    }

    /**
     * Create a list from passed objX parameters
     * @return The resulting List
     */
    public static <T> List<T> toList(T obj1, T obj2)
    {
        List<T> list = new ArrayList<T>(2);

        list.add(obj1);
        list.add(obj2);
        return list;
    }

    /**
     * Create a list from passed objX parameters
     * @return The resulting List
     */
    public static <T> List<T> toList(T obj1, T obj2, T obj3)
    {
        List<T> list = new ArrayList<T>(3);

        list.add(obj1);
        list.add(obj2);
        list.add(obj3);
        return list;
    }

    /**
     * Create a list from passed objX parameters
     * @return The resulting List
     */
    public static <T> List<T> toList(T obj1, T obj2, T obj3, T obj4)
    {
        List<T> list = new ArrayList<T>(4);

        list.add(obj1);
        list.add(obj2);
        list.add(obj3);
        list.add(obj4);
        return list;
    }

    /**
     * Create a list from passed objX parameters
     * @return The resulting List
     */
    public static <T> List<T> toList(T obj1, T obj2, T obj3, T obj4, T obj5)
    {
        List<T> list = new ArrayList<T>(5);

        list.add(obj1);
        list.add(obj2);
        list.add(obj3);
        list.add(obj4);
        list.add(obj5);
        return list;
    }

    /**
     * Create a list from passed objX parameters
     * @return The resulting List
     */
    public static <T> List<T> toList(T obj1, T obj2, T obj3, T obj4, T obj5, T obj6)
    {
        List<T> list = new ArrayList<T>(6);

        list.add(obj1);
        list.add(obj2);
        list.add(obj3);
        list.add(obj4);
        list.add(obj5);
        list.add(obj6);
        return list;
    }

    public static <T> List<T> toList(Collection<T> collection)
    {
        if (collection == null)
            return null;
        if (collection instanceof List)
        {
            return (List<T>) collection;
        }
        else
        {
            return new ArrayList<T>(collection);
        }
    }

    public static <T> List<T> toList(T[] data)
    {
        if (data == null)
        {
            return null;
        }
        List<T> list = new ArrayList<T>(data.length);
        for (int i = 0; i < data.length; i++)
        {
            list.add(data[i]);
        }
        return list;
    }

    /**
     * Adds value to the key entry in theMap, or creates a new one if not
     * already there
     * @param theMap
     * @param key
     * @param value
     */
    public static void addToDoubleInMap(Map<Object, Double> theMap, Object key, Double value)
    {
        Double curValue = theMap.get(key);
        if (curValue != null)
        {
            theMap.put(key, new Double(curValue.doubleValue() + value.doubleValue()));
        }
        else
        {
            theMap.put(key, value);
        }
    }

    /**
     * Parse a locale string Locale object
     * @param localeString
     *        The locale string (en_US)
     * @return Locale The new Locale object or null if no valid locale can be
     *         interpreted
     */
    public static Locale parseLocale(String localeString)
    {
        if (localeString == null || localeString.length() == 0)
        {
            return null;
        }

        Locale locale = null;
        if (localeString.length() == 2)
        {
            // two letter language code
            locale = new Locale(localeString);
        }
        else if (localeString.length() == 5)
        {
            // positions 0-1 language, 3-4 are country
            String language = localeString.substring(0, 2);
            String country = localeString.substring(3, 5);
            locale = new Locale(language, country);
        }
        else if (localeString.length() > 6)
        {
            // positions 0-1 language, 3-4 are country, 6 and on are special
            // extensions
            String language = localeString.substring(0, 2);
            String country = localeString.substring(3, 5);
            String extension = localeString.substring(6);
            locale = new Locale(language, country, extension);
        }
        else
        {
        }

        return locale;
    }

    /**
     * The input can be a String, Locale, or even null and a valid Locale will
     * always be returned; if nothing else works, returns the default locale.
     * @param localeObject
     *        An Object representing the locale
     */
    public static Locale ensureLocale(Object localeObject)
    {
        if (localeObject != null && localeObject instanceof String)
        {
            localeObject = UtilMisc.parseLocale((String) localeObject);
        }
        if (localeObject != null && localeObject instanceof Locale)
        {
            return (Locale) localeObject;
        }
        return Locale.getDefault();
    }

    /**
     * This is meant to be very quick to create and use for small sized maps,
     * perfect for how we usually use UtilMisc.toMap
     */
    protected static class SimpleMap implements Map<String, Object>, java.io.Serializable
    {
        /**
		 * 
		 */
        private static final long serialVersionUID = -3477512558867607926L;

        protected Map<String, Object> realMapIfNeeded = null;

        String[] names;

        Object[] values;

        public SimpleMap()
        {
            names = new String[0];
            values = new Object[0];
        }

        public SimpleMap(String name1, Object value1)
        {
            names = new String[1];
            values = new Object[1];
            this.names[0] = name1;
            this.values[0] = value1;
        }

        public SimpleMap(String name1, Object value1, String name2, Object value2)
        {
            names = new String[2];
            values = new Object[2];
            this.names[0] = name1;
            this.values[0] = value1;
            this.names[1] = name2;
            this.values[1] = value2;
        }

        public SimpleMap(String name1, Object value1, String name2, Object value2, String name3, Object value3)
        {
            names = new String[3];
            values = new Object[3];
            this.names[0] = name1;
            this.values[0] = value1;
            this.names[1] = name2;
            this.values[1] = value2;
            this.names[2] = name3;
            this.values[2] = value3;
        }

        public SimpleMap(String name1, Object value1, String name2, Object value2, String name3, Object value3, String name4, Object value4)
        {
            names = new String[4];
            values = new Object[4];
            this.names[0] = name1;
            this.values[0] = value1;
            this.names[1] = name2;
            this.values[1] = value2;
            this.names[2] = name3;
            this.values[2] = value3;
            this.names[3] = name4;
            this.values[3] = value4;
        }

        protected void makeRealMap()
        {
            realMapIfNeeded = new HashMap<String, Object>();
            for (int i = 0; i < names.length; i++)
            {
                realMapIfNeeded.put(names[i], values[i]);
            }
            this.names = null;
            this.values = null;
        }

        public void clear()
        {
            if (realMapIfNeeded != null)
            {
                realMapIfNeeded.clear();
            }
            else
            {
                realMapIfNeeded = new HashMap<String, Object>();
                names = null;
                values = null;
            }
        }

        public boolean containsKey(Object obj)
        {
            if (realMapIfNeeded != null)
            {
                return realMapIfNeeded.containsKey(obj);
            }
            else
            {
                for (int i = 0; i < names.length; i++)
                {
                    if (obj == null && names[i] == null)
                        return true;
                    if (names[i] != null && names[i].equals(obj))
                        return true;
                }
                return false;
            }
        }

        public boolean containsValue(Object obj)
        {
            if (realMapIfNeeded != null)
            {
                return realMapIfNeeded.containsValue(obj);
            }
            else
            {
                for (int i = 0; i < names.length; i++)
                {
                    if (obj == null && values[i] == null)
                        return true;
                    if (values[i] != null && values[i].equals(obj))
                        return true;
                }
                return false;
            }
        }

        public java.util.Set<Map.Entry<String, Object>> entrySet()
        {
            if (realMapIfNeeded != null)
            {
                return realMapIfNeeded.entrySet();
            }
            else
            {
                this.makeRealMap();
                return realMapIfNeeded.entrySet();
            }
        }

        public Object get(Object obj)
        {
            if (realMapIfNeeded != null)
            {
                return realMapIfNeeded.get(obj);
            }
            else
            {
                for (int i = 0; i < names.length; i++)
                {
                    if (obj == null && names[i] == null)
                        return values[i];
                    if (names[i] != null && names[i].equals(obj))
                        return values[i];
                }
                return null;
            }
        }

        public boolean isEmpty()
        {
            if (realMapIfNeeded != null)
            {
                return realMapIfNeeded.isEmpty();
            }
            else
            {
                if (this.names.length == 0)
                    return true;
                return false;
            }
        }

        public java.util.Set<String> keySet()
        {
            if (realMapIfNeeded != null)
            {
                return realMapIfNeeded.keySet();
            }
            else
            {
                this.makeRealMap();
                return realMapIfNeeded.keySet();
            }
        }

        public Object put(String obj, Object obj1)
        {
            if (realMapIfNeeded != null)
            {
                return realMapIfNeeded.put(obj, obj1);
            }
            else
            {
                this.makeRealMap();
                return realMapIfNeeded.put(obj, obj1);
            }
        }

        public void putAll(java.util.Map<? extends String, ? extends Object> map)
        {
            if (realMapIfNeeded != null)
            {
                realMapIfNeeded.putAll(map);
            }
            else
            {
                this.makeRealMap();
                realMapIfNeeded.putAll(map);
            }
        }

        public Object remove(Object obj)
        {
            if (realMapIfNeeded != null)
            {
                return realMapIfNeeded.remove(obj);
            }
            else
            {
                this.makeRealMap();
                return realMapIfNeeded.remove(obj);
            }
        }

        public int size()
        {
            if (realMapIfNeeded != null)
            {
                return realMapIfNeeded.size();
            }
            else
            {
                return this.names.length;
            }
        }

        public java.util.Collection<Object> values()
        {
            if (realMapIfNeeded != null)
            {
                return realMapIfNeeded.values();
            }
            else
            {
                this.makeRealMap();
                return realMapIfNeeded.values();
            }
        }

        public String toString()
        {
            if (realMapIfNeeded != null)
            {
                return realMapIfNeeded.toString();
            }
            else
            {
                StringBuffer outString = new StringBuffer("{");
                for (int i = 0; i < names.length; i++)
                {
                    if (i > 0)
                        outString.append(',');
                    outString.append('{');
                    outString.append(names[i]);
                    outString.append(',');
                    outString.append(values[i]);
                    outString.append('}');
                }
                outString.append('}');
                return outString.toString();
            }
        }

        public int hashCode()
        {
            if (realMapIfNeeded != null)
            {
                return realMapIfNeeded.hashCode();
            }
            else
            {
                int hashCode = 0;
                for (int i = 0; i < names.length; i++)
                {
                    // note that this calculation is done based on the calc
                    // specified in the Java java.util.Map interface
                    int tempNum = (names[i] == null ? 0 : names[i].hashCode()) ^ (values[i] == null ? 0 : values[i].hashCode());
                    hashCode += tempNum;
                }
                return hashCode;
            }
        }

        public boolean equals(Object obj)
        {
            if (realMapIfNeeded != null)
            {
                return realMapIfNeeded.equals(obj);
            }
            else
            {
                @SuppressWarnings("unchecked")
                Map<String, Object> mapObj = (Map<String, Object>) obj;

                // first check the size
                if (mapObj.size() != names.length)
                    return false;

                // okay, same size, now check each entry
                for (int i = 0; i < names.length; i++)
                {
                    // first check the name
                    if (!mapObj.containsKey(names[i]))
                        return false;

                    // if that passes, check the value
                    Object mapValue = mapObj.get(names[i]);
                    if (mapValue == null)
                    {
                        if (values[i] != null)
                            return false;
                    }
                    else
                    {
                        if (!mapValue.equals(values[i]))
                            return false;
                    }
                }

                return true;
            }
        }
    }

    // Begin Neogia specific : FR1356553 : This method tests if a integer is
    // peer or not and returns true if
    /**
     * return true if numberTotest % 2 == 0
     */
    public static boolean isPeer(int numberToTest)
    {
        if (numberToTest % 2 == 0)
            return true;
        return false;
    }

    public static boolean isEmpty(Object o)
    {
        if (o == null)
            return true;
        return StringUtils.isBlank(o.toString());
    }

    public static boolean isNotEmpty(Object o)
    {
        return !isEmpty(o);
    }
}
