package com.tickets.mgolimbiiev.tickets.core;

import android.widget.Toast;

import com.tickets.mgolimbiiev.tickets.myApplication;

import org.mozilla.javascript.*;

/**
 * Created by Миха on 08.03.2016.
 */
public class TokenParser {
        public  String parseIndexPage(String pageCode){
            if (pageCode == null){
                Toast.makeText(myApplication.getInstance().getApplicationContext(), "nothing to parse", Toast.LENGTH_SHORT).show();
                return null;
            }
            StringBuilder sb = new StringBuilder();
            String temp = pageCode.substring(pageCode.indexOf(";$$_") + 1, pageCode.length());
            temp = temp.substring(0, temp.indexOf(";(function"));
            String parsed = patseTokenFromDecodedString(temp);
            String token = parsed.substring(parsed.indexOf(",")+1, parsed.indexOf("\")"));
            return token;
        }
    private String patseTokenFromDecodedString(String html) {

        // Every Rhino VM begins with the enter()
        // This Context is not Android's Context
        Context rhino = Context.enter();
        String result = null;

        // Turn off optimization to make Rhino Android compatible
        rhino.setOptimizationLevel(-1);
        try {
            Scriptable scope = rhino.initStandardObjects();

            // Note the forth argument is 1, which means the JavaScript source has
            // been compressed to only one line using something like YUI
            rhino.evaluateString(scope, jsCode, "JavaScript", 1, null);

            // Get the functionName defined in JavaScriptCode
            Object obj = scope.get("result", scope);
                Object fObj = scope.get("jjdecode", scope);

                if (!(fObj instanceof Function)) {
                        System.out.println("f is undefined or not a function.");
                } else {
                        Object functionArgs[] = { html };
                        Function f = (Function)fObj;
                        Object token = f.call(rhino, scope, scope, functionArgs);
                        String report =  Context.toString(token);
                        result = report;
                        System.out.println(report);
                }
        } catch (JavaScriptException e) {
            e.printStackTrace();
        } finally {
            Context.exit();
        }
        return result;
    }
        public static void main(String[] args){
                String token = new TokenParser().patseTokenFromDecodedString(temp);
                System.out.println(token);

        }

    private static String temp = "$$_=~[];$$_={___:++$$_,$$$$:(![]+\"\")[$$_],__$:++$$_,$_$_:(![]+\"\")[$$_],_$_:++$$_,$_$$:({}+\"\")[$$_],$$_$:($$_[$$_]+\"\")[$$_],_$$:++$$_,$$$_:(!\"\"+\"\")[$$_],$__:++$$_,$_$:++$$_,$$__:({}+\"\")[$$_],$$_:++$$_,$$$:++$$_,$___:++$$_,$__$:++$$_};$$_.$_=($$_.$_=$$_+\"\")[$$_.$_$]+($$_._$=$$_.$_[$$_.__$])+($$_.$$=($$_.$+\"\")[$$_.__$])+((!$$_)+\"\")[$$_._$$]+($$_.__=$$_.$_[$$_.$$_])+($$_.$=(!\"\"+\"\")[$$_.__$])+($$_._=(!\"\"+\"\")[$$_._$_])+$$_.$_[$$_.$_$]+$$_.__+$$_._$+$$_.$;$$_.$$=$$_.$+(!\"\"+\"\")[$$_._$$]+$$_.__+$$_._+$$_.$+$$_.$$;$$_.$=($$_.___)[$$_.$_][$$_.$_];$$_.$($$_.$($$_.$$+\"\\\"\"+(![]+\"\")[$$_._$_]+$$_._$+$$_.$$__+$$_.$_$_+(![]+\"\")[$$_._$_]+\"\\\\\"+$$_.__$+$$_._$_+$$_._$$+$$_.__+$$_._$+\"\\\\\"+$$_.__$+$$_.$$_+$$_._$_+$$_.$_$_+\"\\\\\"+$$_.__$+$$_.$__+$$_.$$$+$$_.$$$_+\".\\\\\"+$$_.__$+$$_.$$_+$$_._$$+$$_.$$$_+$$_.__+\"\\\\\"+$$_.__$+$$_.__$+$$_.__$+$$_.__+$$_.$$$_+\"\\\\\"+$$_.__$+$$_.$_$+$$_.$_$+\"(\\\\\\\"\\\\\"+$$_.__$+$$_.$__+$$_.$$$+\"\\\\\"+$$_.__$+$$_.$$_+$$_.$$_+\"-\"+$$_.__+$$_._$+\"\\\\\"+$$_.__$+$$_.$_$+$$_._$$+$$_.$$$_+\"\\\\\"+$$_.__$+$$_.$_$+$$_.$$_+\"\\\\\\\",\\\\\"+$$_.$__+$$_.___+\"\\\\\\\"\"+$$_.$__+$$_.$$$+$$_.$_$_+$$_.$_$+$$_.$__+$$_.$$$$+$$_.$___+$$_.$$$+$$_.___+$$_.$__$+$$_._$$+$$_.__$+$$_.$$_$+$$_.$___+$$_.$_$$+$$_.$_$+$$_.$__$+$$_.$$$_+$$_.__$+$$_.$___+$$_.$$$_+$$_.__$+$$_.$$_+$$_.___+$$_._$_+$$_.$_$+$$_.$__+$$_.$$$$+$$_._$_+$$_.___+$$_.$$$_+$$_.$___+\"\\\\\\\");\"+\"\\\"\")())()";

    private static String jsCode = "function jjdecode(t)\n" +
            "        {\n" +
            "            var result;\n" +
            "            \n" +
            "            //get string from src\n" +
            "            \n" +
            "            //clean it\n" +
            "            t.replace(/^\\s+|\\s+$/g, \"\");\n" +
            "            \n" +
            "            var startpos;\n" +
            "            var endpos;\n" +
            "            var gv;\n" +
            "            var  gvl;\t\n" +
            "            \n" +
            "            if (t.indexOf(\"\\\"\\'\\\\\\\"+\\'+\\\",\") == 0) //palindrome check\n" +
            "            {\n" +
            "                //locate jjcode\n" +
            "                startpos\t= t.indexOf('$$+\"\\\\\"\"+') + 8;\n" +
            "                endpos\t\t= t.indexOf('\"\\\\\"\")())()');\n" +
            "                    \n" +
            "                //get gv\n" +
            "                gv\t= t.substring((t.indexOf('\"\\'\\\\\"+\\'+\",')+9), t.indexOf(\"=~[]\"));\n" +
            "                gvl\t= gv.length;\n" +
            "            }\n" +
            "            else\n" +
            "            {\n" +
            "                //get gv\n" +
            "                gv\t= t.substr(0, t.indexOf(\"=\"));\n" +
            "                gvl\t= gv.length;\n" +
            "                \n" +
            "                //locate jjcode\n" +
            "                startpos\t= t.indexOf('\"\\\\\"\"+') + 5;\n" +
            "                endpos\t\t= t.indexOf('\"\\\\\"\")())()');\t\n" +
            "            }\n" +
            "                        \n" +
            "            if (startpos == endpos)\n" +
            "            {\n" +
            "                alert(\"No data !\");\n" +
            "                return;\n" +
            "            }\n" +
            "            \n" +
            "            //start decoding\n" +
            "            var data = t.substring(startpos, endpos);\n" +
            "                \n" +
            "            //hex decode string\n" +
            "            var b=[ \"___+\", \"__$+\", \"_$_+\", \"_$$+\", \"$__+\", \"$_$+\", \"$$_+\", \"$$$+\", \"$___+\", \"$__$+\", \"$_$_+\", \"$_$$+\", \"$$__+\", \"$$_$+\", \"$$$_+\", \"$$$$+\" ];\n" +
            "            \n" +
            "            //lotu\n" +
            "            var str_l = \"(![]+\\\"\\\")[\" + gv + \"._$_]+\";\n" +
            "            var str_o = gv + \"._$+\";\n" +
            "            var str_t = gv + \".__+\";\n" +
            "            var str_u = gv + \"._+\";\n" +
            "            \n" +
            "            //0123456789abcdef\n" +
            "            var str_hex = gv + \".\";\n" +
            "            \n" +
            "            //s\n" +
            "            var str_s = '\"';\n" +
            "            var gvsig = gv + \".\";\n" +
            "            \n" +
            "            var str_quote = '\\\\\\\\\\\\\"';\n" +
            "            var str_slash = '\\\\\\\\\\\\\\\\';\n" +
            "            \n" +
            "            var str_lower = \"\\\\\\\\\\\"+\";\n" +
            "            var str_upper = \"\\\\\\\\\\\"+\" + gv + \"._+\";\n" +
            "            \n" +
            "            var str_end\t= '\"+'; //end of s loop\n" +
            "            \n" +
            "            \n" +
            "            \n" +
            "            while(data != \"\")\n" +
            "            {\n" +
            "                //l o t u\n" +
            "                if (0 == data.indexOf(str_l))\n" +
            "                {\n" +
            "                    data = data.substr(str_l.length);\n" +
            "                    result = result + (\"l\");\n" +
            "                    continue;\n" +
            "                }\n" +
            "                else if (0 == data.indexOf(str_o))\n" +
            "                {\n" +
            "                    data = data.substr(str_o.length);\n" +
            "                    result = result + (\"o\");\n" +
            "                    continue;\n" +
            "                }\n" +
            "                else if (0 == data.indexOf(str_t))\n" +
            "                {\n" +
            "                    data = data.substr(str_t.length);\n" +
            "                    result = result + (\"t\");\n" +
            "                    continue;\n" +
            "                }\n" +
            "                else if (0 == data.indexOf(str_u))\n" +
            "                {\n" +
            "                    data = data.substr(str_u.length);\n" +
            "                    result = result + (\"u\");\n" +
            "                    continue;\n" +
            "                }\n" +
            "                \n" +
            "                //0123456789abcdef\n" +
            "                if (0 == data.indexOf(str_hex))\n" +
            "                {\n" +
            "                    data = data.substr(str_hex.length);\n" +
            "                    \n" +
            "                    //check every element of hex decode string for a match \n" +
            "                    var i = 0;\t\t\t\t\t\t\n" +
            "                    for (i = 0; i < b.length; i++)\n" +
            "                    {\n" +
            "                        if (0 == data.indexOf(b[i]))\n" +
            "                        {\n" +
            "                            data = data.substr( (b[i]).length );\n" +
            "                            result = result + (i.toString(16));\n" +
            "                            break;\n" +
            "                        }\n" +
            "                    }\n" +
            "                    continue;\n" +
            "                }\n" +
            "                \n" +
            "                //start of s block\n" +
            "                if (0 == data.indexOf(str_s))\n" +
            "                {\n" +
            "                    data = data.substr(str_s.length);\n" +
            "                    \n" +
            "                    //check if \"R\n" +
            "                    if (0 == data.indexOf(str_upper)) // r4 n >= 128\n" +
            "                    {\n" +
            "                        data = data.substr(str_upper.length); //skip sig\n" +
            "                        \n" +
            "                        var ch_str = \"\";\t\t\t\t\n" +
            "                        for (j = 0; j < 2; j++) //shouldn't be more than 2 hex chars\n" +
            "                        {\n" +
            "                            //gv + \".\"+b[ c ]\t\t\t\t\n" +
            "                            if (0 == data.indexOf(gvsig))\n" +
            "                            {\n" +
            "                                data = data.substr(gvsig.length); //skip gvsig\t\n" +
            "                                for (k = 0; k < b.length; k++)\t//for every entry in b\n" +
            "                                {\t\t\t\t\t\t\n" +
            "                                    if (0 == data.indexOf(b[k]))\n" +
            "                                    {\n" +
            "                                        data = data.substr(b[k].length);\n" +
            "                                        ch_str += k.toString(16) + \"\";\t\t\t\t\t\t\t\n" +
            "                                        break;\n" +
            "                                    }\n" +
            "                                }\t\t\t\t\t\t\n" +
            "                            }\n" +
            "                            else\n" +
            "                            {\n" +
            "                                break; //done\n" +
            "                            }\t\t\t\t\t\t\t\t\n" +
            "                        }\n" +
            "                        \n" +
            "                        result = result + (String.fromCharCode(parseInt(ch_str,16)));\n" +
            "                        continue;\n" +
            "                    }\n" +
            "                    else if (0 == data.indexOf(str_lower)) //r3 check if \"R // n < 128\n" +
            "                    {\n" +
            "                        data = data.substr(str_lower.length); //skip sig\n" +
            "                        \n" +
            "                        var ch_str = \"\";\n" +
            "                        var ch_lotux = \"\"\n" +
            "                        var temp = \"\";\n" +
            "                        var b_checkR1 = 0;\n" +
            "                        for (j = 0; j < 3; j++) //shouldn't be more than 3 octal chars\n" +
            "                        {\n" +
            "                        \n" +
            "                            if (j > 1) //lotu check\n" +
            "                            {\t\t\t\t\t\t\t\t\n" +
            "                                if (0 == data.indexOf(str_l))\n" +
            "                                {\n" +
            "                                    data = data.substr(str_l.length);\n" +
            "                                    ch_lotux = \"l\";\n" +
            "                                    break;\n" +
            "                                }\n" +
            "                                else if (0 == data.indexOf(str_o))\n" +
            "                                {\n" +
            "                                    data = data.substr(str_o.length);\n" +
            "                                    ch_lotux = \"o\";\n" +
            "                                    break;\n" +
            "                                }\n" +
            "                                else if (0 == data.indexOf(str_t))\n" +
            "                                {\n" +
            "                                    data = data.substr(str_t.length);\n" +
            "                                    ch_lotux = \"t\";\n" +
            "                                    break;\n" +
            "                                }\n" +
            "                                else if (0 == data.indexOf(str_u))\n" +
            "                                {\n" +
            "                                    data = data.substr(str_u.length);\n" +
            "                                    ch_lotux = \"u\";\n" +
            "                                    break;\n" +
            "                                }\t\t\t\t\t\t\n" +
            "                            }\n" +
            "                        \n" +
            "                            //gv + \".\"+b[ c ]\t\t\t\t\t\t\t\n" +
            "                            if (0 == data.indexOf(gvsig))\n" +
            "                            {\n" +
            "                                temp = data.substr(gvsig.length); \n" +
            "                                for (k = 0; k < 8; k++)\t//for every entry in b octal\n" +
            "                                {\t\t\t\t\t\t\n" +
            "                                    if (0 == temp.indexOf(b[k]))\n" +
            "                                    {\n" +
            "                                        if (parseInt(ch_str + k + \"\",8) > 128)\n" +
            "                                        {\n" +
            "                                            b_checkR1 = 1;\n" +
            "                                            break;\n" +
            "                                        }\t\t\t\t\t\t\t\t\n" +
            "                                        \n" +
            "                                        ch_str += k + \"\";\t\t\t\t\t\t\t\t\t\t\n" +
            "                                        data = data.substr(gvsig.length); //skip gvsig\n" +
            "                                        data = data.substr(b[k].length);\n" +
            "                                        break;\n" +
            "                                    }\n" +
            "                                }\n" +
            "                                \n" +
            "                                if (1 == b_checkR1)\n" +
            "                                {\n" +
            "                                    if (0 == data.indexOf(str_hex)) //0123456789abcdef\n" +
            "                                    {\n" +
            "                                        data = data.substr(str_hex.length);\n" +
            "                                        \n" +
            "                                        //check every element of hex decode string for a match \n" +
            "                                        var i = 0;\t\t\t\t\t\t\n" +
            "                                        for (i = 0; i < b.length; i++)\n" +
            "                                        {\n" +
            "                                            if (0 == data.indexOf(b[i]))\n" +
            "                                            {\n" +
            "                                                data = data.substr( (b[i]).length );\n" +
            "                                                ch_lotux = i.toString(16);\n" +
            "                                                break;\n" +
            "                                            }\n" +
            "                                        }\n" +
            "                                        \n" +
            "                                        break;\n" +
            "                                    }\n" +
            "                                }\t\t\t\t\t\t\t\t\n" +
            "                            }\n" +
            "                            else\n" +
            "                            {\t\t\t\t\t\t\t\t\n" +
            "                                break; //done\n" +
            "                            }\t\t\t\t\t\t\t\t\n" +
            "                        }\n" +
            "                        result = result + (String.fromCharCode(parseInt(ch_str,8)) + ch_lotux);\n" +
            "                        continue; //step result = result +  of the while loop\n" +
            "                    }\n" +
            "                    else //\"S ----> \"SR or \"S+\n" +
            "                    {\n" +
            "                        \n" +
            "                        // if there is, loop s until R 0r +\n" +
            "                        // if there is no matching s block, throw error\n" +
            "                        \n" +
            "                        var match = 0;\n" +
            "                        var n;\n" +
            "                        //searching for mathcing pure s block\n" +
            "                        while(true)\n" +
            "                        {\n" +
            "                            n = data.charCodeAt( 0 );\t\t\t\t\n" +
            "                            if (0 == data.indexOf(str_quote))\n" +
            "                            {\n" +
            "                                data = data.substr(str_quote.length);\n" +
            "                                result = result + ('\"');\n" +
            "                                match += 1;\n" +
            "                                continue;\n" +
            "                            }\n" +
            "                            else if (0 == data.indexOf(str_slash))\n" +
            "                            {\n" +
            "                                data = data.substr(str_slash.length);\n" +
            "                                result = result + ('\\\\');\n" +
            "                                match += 1;\n" +
            "                                continue;\n" +
            "                            }\n" +
            "                            else if (0 == data.indexOf(str_end))\t//reached end off S block ? +\n" +
            "                            {\n" +
            "                                if (match == 0)\n" +
            "                                {\n" +
            "                                    alert(\"+ no match S block: \"+data);\n" +
            "                                    return;\n" +
            "                                }\n" +
            "                                data = data.substr(str_end.length);\n" +
            "                                \n" +
            "                                break; //step result = result +  of the while loop\n" +
            "                            }\n" +
            "                            else if (0 == data.indexOf(str_upper)) //r4 reached end off S block ? - check if \"R n >= 128\n" +
            "                            {\t\t\t\t\t\t\n" +
            "                                if (match == 0)\n" +
            "                                {\n" +
            "                                    alert(\"no match S block n>128: \"+data);\n" +
            "                                    return;\n" +
            "                                }\n" +
            "                            \n" +
            "                                data = data.substr(str_upper.length); //skip sig\n" +
            "                                \n" +
            "                                var ch_str = \"\";\n" +
            "                                var ch_lotux = \"\";\n" +
            "                                for (j = 0; j < 10; j++) //shouldn't be more than 10 hex chars\n" +
            "                                {\n" +
            "                                \n" +
            "                                    if (j > 1) //lotu check\n" +
            "                                    {\t\t\t\t\t\t\t\t\n" +
            "                                        if (0 == data.indexOf(str_l))\n" +
            "                                        {\n" +
            "                                            data = data.substr(str_l.length);\n" +
            "                                            ch_lotux = \"l\";\n" +
            "                                            break;\n" +
            "                                        }\n" +
            "                                        else if (0 == data.indexOf(str_o))\n" +
            "                                        {\n" +
            "                                            data = data.substr(str_o.length);\n" +
            "                                            ch_lotux = \"o\";\n" +
            "                                            break;\n" +
            "                                        }\n" +
            "                                        else if (0 == data.indexOf(str_t))\n" +
            "                                        {\n" +
            "                                            data = data.substr(str_t.length);\n" +
            "                                            ch_lotux = \"t\";\n" +
            "                                            break;\n" +
            "                                        }\n" +
            "                                        else if (0 == data.indexOf(str_u))\n" +
            "                                        {\n" +
            "                                            data = data.substr(str_u.length);\n" +
            "                                            ch_lotux = \"u\";\n" +
            "                                            break;\n" +
            "                                        }\n" +
            "                                    }\n" +
            "                                \n" +
            "                                    //gv + \".\"+b[ c ]\t\t\t\t\n" +
            "                                    if (0 == data.indexOf(gvsig))\n" +
            "                                    {\n" +
            "                                        data = data.substr(gvsig.length); //skip gvsig\n" +
            "                                        for (k = 0; k < b.length; k++)\t//for every entry in b\n" +
            "                                        {\t\t\t\t\t\t\n" +
            "                                            if (0 == data.indexOf(b[k]))\n" +
            "                                            {\n" +
            "                                                data = data.substr(b[k].length);\n" +
            "                                                ch_str += k.toString(16) + \"\";\t\t\t\t\t\t\t\n" +
            "                                                break;\n" +
            "                                            }\n" +
            "                                        }\t\t\t\t\t\t\n" +
            "                                    }\n" +
            "                                    else\n" +
            "                                    {\n" +
            "                                        break; //done\n" +
            "                                    }\t\t\t\t\t\t\t\t\n" +
            "                                }\n" +
            "                                \n" +
            "                                result = result + (String.fromCharCode(parseInt(ch_str,16)));\n" +
            "                                break; //step result = result +  of the while loop\n" +
            "                            }\n" +
            "                            else if (0 == data.indexOf(str_lower)) //r3 check if \"R // n < 128\n" +
            "                            {\n" +
            "                                if (match == 0)\n" +
            "                                {\n" +
            "                                    alert(\"no match S block n<128: \"+data);\n" +
            "                                    return;\n" +
            "                                }\n" +
            "                            \n" +
            "                                data = data.substr(str_lower.length); //skip sig\n" +
            "                                \n" +
            "                                var ch_str = \"\";\n" +
            "                                var ch_lotux = \"\"\n" +
            "                                var temp = \"\";\n" +
            "                                var b_checkR1 = 0;\n" +
            "                                for (j = 0; j < 3; j++) //shouldn't be more than 3 octal chars\n" +
            "                                {\n" +
            "                                \n" +
            "                                    if (j > 1) //lotu check\n" +
            "                                    {\t\t\t\t\t\t\t\t\n" +
            "                                        if (0 == data.indexOf(str_l))\n" +
            "                                        {\n" +
            "                                            data = data.substr(str_l.length);\n" +
            "                                            ch_lotux = \"l\";\n" +
            "                                            break;\n" +
            "                                        }\n" +
            "                                        else if (0 == data.indexOf(str_o))\n" +
            "                                        {\n" +
            "                                            data = data.substr(str_o.length);\n" +
            "                                            ch_lotux = \"o\";\n" +
            "                                            break;\n" +
            "                                        }\n" +
            "                                        else if (0 == data.indexOf(str_t))\n" +
            "                                        {\n" +
            "                                            data = data.substr(str_t.length);\n" +
            "                                            ch_lotux = \"t\";\n" +
            "                                            break;\n" +
            "                                        }\n" +
            "                                        else if (0 == data.indexOf(str_u))\n" +
            "                                        {\n" +
            "                                            data = data.substr(str_u.length);\n" +
            "                                            ch_lotux = \"u\";\n" +
            "                                            break;\n" +
            "                                        }\t\t\t\t\t\t\t\t\n" +
            "                                    }\n" +
            "                                \n" +
            "                                    //gv + \".\"+b[ c ]\t\t\t\t\t\t\t\n" +
            "                                    if (0 == data.indexOf(gvsig))\n" +
            "                                    {\n" +
            "                                        temp = data.substr(gvsig.length); \n" +
            "                                        for (k = 0; k < 8; k++)\t//for every entry in b octal\n" +
            "                                        {\t\t\t\t\t\t\n" +
            "                                            if (0 == temp.indexOf(b[k]))\n" +
            "                                            {\n" +
            "                                                if (parseInt(ch_str + k + \"\",8) > 128)\n" +
            "                                                {\n" +
            "                                                    b_checkR1 = 1;\n" +
            "                                                    break;\n" +
            "                                                }\t\t\t\t\t\t\t\t\n" +
            "                                                \n" +
            "                                                ch_str += k + \"\";\t\t\t\t\t\t\t\t\t\t\n" +
            "                                                data = data.substr(gvsig.length); //skip gvsig\n" +
            "                                                data = data.substr(b[k].length);\n" +
            "                                                break;\n" +
            "                                            }\n" +
            "                                        }\n" +
            "                                        \n" +
            "                                        if (1 == b_checkR1)\n" +
            "                                        {\n" +
            "                                            if (0 == data.indexOf(str_hex)) //0123456789abcdef\n" +
            "                                            {\n" +
            "                                                data = data.substr(str_hex.length);\n" +
            "                                                \n" +
            "                                                //check every element of hex decode string for a match \n" +
            "                                                var i = 0;\t\t\t\t\t\t\n" +
            "                                                for (i = 0; i < b.length; i++)\n" +
            "                                                {\n" +
            "                                                    if (0 == data.indexOf(b[i]))\n" +
            "                                                    {\n" +
            "                                                        data = data.substr( (b[i]).length );\n" +
            "                                                        ch_lotux = i.toString(16);\n" +
            "                                                        break;\n" +
            "                                                    }\n" +
            "                                                }\n" +
            "                                            }\n" +
            "                                        }\t\t\t\t\t\t\t\t\n" +
            "                                    }\n" +
            "                                    else\n" +
            "                                    {\t\t\t\t\t\t\t\t\n" +
            "                                        break; //done\n" +
            "                                    }\t\t\t\t\t\t\t\t\n" +
            "                                }\n" +
            "                                result = result + (String.fromCharCode(parseInt(ch_str,8)) + ch_lotux);\n" +
            "                                break; //step result = result +  of the while loop\n" +
            "                            }\t \n" +
            "                            else if( (0x21 <= n && n <= 0x2f) || (0x3A <= n && n <= 0x40) || ( 0x5b <= n && n <= 0x60 ) || ( 0x7b <= n && n <= 0x7f ) )\n" +
            "                            {\n" +
            "                                result = result + (data.charAt( 0 ));\n" +
            "                                data = data.substr( 1 );\n" +
            "                                match += 1;\n" +
            "                            }\n" +
            "                            \n" +
            "                        }\t\t\t\n" +
            "                        continue;\t\t\t\n" +
            "                    }\n" +
            "                }\n" +
            "                    \n" +
            "                alert(\"no match : \"+data);\n" +
            "                break;\n" +
            "            }\n" +
            "            \n" +
            "        return result;\n" +
            "        }";



}
