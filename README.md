# idboxplugin

KYC

## Install

```bash
npm install idboxplugin
npx cap sync
```

## API

<docgen-index>

* [`initWithHawkCredentials(...)`](#initwithhawkcredentials)
* [`registerRequest(...)`](#registerrequest)
* [`getNextStep()`](#getnextstep)
* [`getRequestId()`](#getrequestid)
* [`pleaseWait()`](#pleasewait)
* [`scanSelfie(...)`](#scanselfie)
* [`videoCall(...)`](#videocall)
* [`videoCallQueue(...)`](#videocallqueue)
* [`videoCallVerification()`](#videocallverification)
* [`createOtp()`](#createotp)
* [`resendOtp()`](#resendotp)
* [`contractForm(...)`](#contractform)
* [`contractFormAthex(...)`](#contractformathex)
* [`startListeningSignalR(...)`](#startlisteningsignalr)
* [`startListeningVideoSignalR(...)`](#startlisteningvideosignalr)
* [`scan1SIdentity(...)`](#scan1sidentity)
* [`scan2SIdentity(...)`](#scan2sidentity)
* [`scanAdditionalDocuments(...)`](#scanadditionaldocuments)
* [`startVideoSession(...)`](#startvideosession)
* [Interfaces](#interfaces)

</docgen-index>

<docgen-api>
<!--Update the source file JSDoc comments and rerun docgen to update the docs below-->

### initWithHawkCredentials(...)

```typescript
initWithHawkCredentials(options: { options: String; }) => Promise<{ options: String; }>
```

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code>{ options: <a href="#string">String</a>; }</code> |

**Returns:** <code>Promise&lt;{ options: <a href="#string">String</a>; }&gt;</code>

--------------------


### registerRequest(...)

```typescript
registerRequest(options: { options: String; }) => Promise<{ options: String; }>
```

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code>{ options: <a href="#string">String</a>; }</code> |

**Returns:** <code>Promise&lt;{ options: <a href="#string">String</a>; }&gt;</code>

--------------------


### getNextStep()

```typescript
getNextStep() => Promise<{ options: any; }>
```

**Returns:** <code>Promise&lt;{ options: any; }&gt;</code>

--------------------


### getRequestId()

```typescript
getRequestId() => Promise<{ response: any; }>
```

**Returns:** <code>Promise&lt;{ response: any; }&gt;</code>

--------------------


### pleaseWait()

```typescript
pleaseWait() => Promise<{ options: any; }>
```

**Returns:** <code>Promise&lt;{ options: any; }&gt;</code>

--------------------


### scanSelfie(...)

```typescript
scanSelfie(options: { options: String; }) => Promise<{ options: any; }>
```

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code>{ options: <a href="#string">String</a>; }</code> |

**Returns:** <code>Promise&lt;{ options: any; }&gt;</code>

--------------------


### videoCall(...)

```typescript
videoCall(options: { options: String; }) => Promise<{ options: any; }>
```

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code>{ options: <a href="#string">String</a>; }</code> |

**Returns:** <code>Promise&lt;{ options: any; }&gt;</code>

--------------------


### videoCallQueue(...)

```typescript
videoCallQueue(options: { options: String; }) => Promise<{ options: any; }>
```

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code>{ options: <a href="#string">String</a>; }</code> |

**Returns:** <code>Promise&lt;{ options: any; }&gt;</code>

--------------------


### videoCallVerification()

```typescript
videoCallVerification() => Promise<{ options: any; }>
```

**Returns:** <code>Promise&lt;{ options: any; }&gt;</code>

--------------------


### createOtp()

```typescript
createOtp() => Promise<{ options: any; }>
```

**Returns:** <code>Promise&lt;{ options: any; }&gt;</code>

--------------------


### resendOtp()

```typescript
resendOtp() => Promise<{ options: any; }>
```

**Returns:** <code>Promise&lt;{ options: any; }&gt;</code>

--------------------


### contractForm(...)

```typescript
contractForm(options: { options: String; }) => Promise<{ Result: { Data: string; }; }>
```

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code>{ options: <a href="#string">String</a>; }</code> |

**Returns:** <code>Promise&lt;{ Result: { Data: string; }; }&gt;</code>

--------------------


### contractFormAthex(...)

```typescript
contractFormAthex(options: { options: String; }) => Promise<{ Result: { Data: string; }; }>
```

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code>{ options: <a href="#string">String</a>; }</code> |

**Returns:** <code>Promise&lt;{ Result: { Data: string; }; }&gt;</code>

--------------------


### startListeningSignalR(...)

```typescript
startListeningSignalR(options: { options: String; }) => Promise<{ options: any; }>
```

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code>{ options: <a href="#string">String</a>; }</code> |

**Returns:** <code>Promise&lt;{ options: any; }&gt;</code>

--------------------


### startListeningVideoSignalR(...)

```typescript
startListeningVideoSignalR(options: { options: String; }) => Promise<{ options: any; }>
```

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code>{ options: <a href="#string">String</a>; }</code> |

**Returns:** <code>Promise&lt;{ options: any; }&gt;</code>

--------------------


### scan1SIdentity(...)

```typescript
scan1SIdentity(options: { options: String; }) => Promise<{ options: any; }>
```

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code>{ options: <a href="#string">String</a>; }</code> |

**Returns:** <code>Promise&lt;{ options: any; }&gt;</code>

--------------------


### scan2SIdentity(...)

```typescript
scan2SIdentity(options: { options: String; }) => Promise<{ options: any; }>
```

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code>{ options: <a href="#string">String</a>; }</code> |

**Returns:** <code>Promise&lt;{ options: any; }&gt;</code>

--------------------


### scanAdditionalDocuments(...)

```typescript
scanAdditionalDocuments(options: { options: String; }) => Promise<{ options: any; }>
```

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code>{ options: <a href="#string">String</a>; }</code> |

**Returns:** <code>Promise&lt;{ options: any; }&gt;</code>

--------------------


### startVideoSession(...)

```typescript
startVideoSession(options: { options: String; }) => Promise<{ options: any; }>
```

| Param         | Type                                                    |
| ------------- | ------------------------------------------------------- |
| **`options`** | <code>{ options: <a href="#string">String</a>; }</code> |

**Returns:** <code>Promise&lt;{ options: any; }&gt;</code>

--------------------


### Interfaces


#### String

Allows manipulation and formatting of text strings and determination and location of substrings within strings.

| Prop         | Type                | Description                                                  |
| ------------ | ------------------- | ------------------------------------------------------------ |
| **`length`** | <code>number</code> | Returns the length of a <a href="#string">String</a> object. |

| Method                | Signature                                                                                                                      | Description                                                                                                                                   |
| --------------------- | ------------------------------------------------------------------------------------------------------------------------------ | --------------------------------------------------------------------------------------------------------------------------------------------- |
| **toString**          | () =&gt; string                                                                                                                | Returns a string representation of a string.                                                                                                  |
| **charAt**            | (pos: number) =&gt; string                                                                                                     | Returns the character at the specified index.                                                                                                 |
| **charCodeAt**        | (index: number) =&gt; number                                                                                                   | Returns the Unicode value of the character at the specified location.                                                                         |
| **concat**            | (...strings: string[]) =&gt; string                                                                                            | Returns a string that contains the concatenation of two or more strings.                                                                      |
| **indexOf**           | (searchString: string, position?: number \| undefined) =&gt; number                                                            | Returns the position of the first occurrence of a substring.                                                                                  |
| **lastIndexOf**       | (searchString: string, position?: number \| undefined) =&gt; number                                                            | Returns the last occurrence of a substring in the string.                                                                                     |
| **localeCompare**     | (that: string) =&gt; number                                                                                                    | Determines whether two strings are equivalent in the current locale.                                                                          |
| **match**             | (regexp: string \| <a href="#regexp">RegExp</a>) =&gt; <a href="#regexpmatcharray">RegExpMatchArray</a> \| null                | Matches a string with a regular expression, and returns an array containing the results of that search.                                       |
| **replace**           | (searchValue: string \| <a href="#regexp">RegExp</a>, replaceValue: string) =&gt; string                                       | Replaces text in a string, using a regular expression or search string.                                                                       |
| **replace**           | (searchValue: string \| <a href="#regexp">RegExp</a>, replacer: (substring: string, ...args: any[]) =&gt; string) =&gt; string | Replaces text in a string, using a regular expression or search string.                                                                       |
| **search**            | (regexp: string \| <a href="#regexp">RegExp</a>) =&gt; number                                                                  | Finds the first substring match in a regular expression search.                                                                               |
| **slice**             | (start?: number \| undefined, end?: number \| undefined) =&gt; string                                                          | Returns a section of a string.                                                                                                                |
| **split**             | (separator: string \| <a href="#regexp">RegExp</a>, limit?: number \| undefined) =&gt; string[]                                | Split a string into substrings using the specified separator and return them as an array.                                                     |
| **substring**         | (start: number, end?: number \| undefined) =&gt; string                                                                        | Returns the substring at the specified location within a <a href="#string">String</a> object.                                                 |
| **toLowerCase**       | () =&gt; string                                                                                                                | Converts all the alphabetic characters in a string to lowercase.                                                                              |
| **toLocaleLowerCase** | (locales?: string \| string[] \| undefined) =&gt; string                                                                       | Converts all alphabetic characters to lowercase, taking into account the host environment's current locale.                                   |
| **toUpperCase**       | () =&gt; string                                                                                                                | Converts all the alphabetic characters in a string to uppercase.                                                                              |
| **toLocaleUpperCase** | (locales?: string \| string[] \| undefined) =&gt; string                                                                       | Returns a string where all alphabetic characters have been converted to uppercase, taking into account the host environment's current locale. |
| **trim**              | () =&gt; string                                                                                                                | Removes the leading and trailing white space and line terminator characters from a string.                                                    |
| **substr**            | (from: number, length?: number \| undefined) =&gt; string                                                                      | Gets a substring beginning at the specified location and having the specified length.                                                         |
| **valueOf**           | () =&gt; string                                                                                                                | Returns the primitive value of the specified object.                                                                                          |


#### RegExpMatchArray

| Prop        | Type                |
| ----------- | ------------------- |
| **`index`** | <code>number</code> |
| **`input`** | <code>string</code> |


#### RegExp

| Prop             | Type                 | Description                                                                                                                                                          |
| ---------------- | -------------------- | -------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **`source`**     | <code>string</code>  | Returns a copy of the text of the regular expression pattern. Read-only. The regExp argument is a Regular expression object. It can be a variable name or a literal. |
| **`global`**     | <code>boolean</code> | Returns a Boolean value indicating the state of the global flag (g) used with a regular expression. Default is false. Read-only.                                     |
| **`ignoreCase`** | <code>boolean</code> | Returns a Boolean value indicating the state of the ignoreCase flag (i) used with a regular expression. Default is false. Read-only.                                 |
| **`multiline`**  | <code>boolean</code> | Returns a Boolean value indicating the state of the multiline flag (m) used with a regular expression. Default is false. Read-only.                                  |
| **`lastIndex`**  | <code>number</code>  |                                                                                                                                                                      |

| Method      | Signature                                                                     | Description                                                                                                                   |
| ----------- | ----------------------------------------------------------------------------- | ----------------------------------------------------------------------------------------------------------------------------- |
| **exec**    | (string: string) =&gt; <a href="#regexpexecarray">RegExpExecArray</a> \| null | Executes a search on a string using a regular expression pattern, and returns an array containing the results of that search. |
| **test**    | (string: string) =&gt; boolean                                                | Returns a Boolean value that indicates whether or not a pattern exists in a searched string.                                  |
| **compile** | () =&gt; this                                                                 |                                                                                                                               |


#### RegExpExecArray

| Prop        | Type                |
| ----------- | ------------------- |
| **`index`** | <code>number</code> |
| **`input`** | <code>string</code> |

</docgen-api>
