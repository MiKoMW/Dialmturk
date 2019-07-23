# Dialmturk

This is a toolkit to creat, deploy and proof Amazon Mturk Hits for Dialogue Tasks.

People could be lazy and do not read the Amazon Document to publish their own HIT. Currently, this toolkit is compatible with the DialCrowd.

## How to use

##### Step 1 Sign in or Creat an Amazon Mturk Account.

You could Sign in or creat an Mturk request account [here](https://requester.mturk.com/begin_signin).

##### Step 2 Create an Amazon Web Services (AWS) Account.
 
You will need an AWS Account. If you don't have one, you could sign up [here](https://aws-portal.amazon.com/gp/aws/developer/registration/index.html).


##### Step 3 Link Your AWS Account with Your MTurk Requester Account.

Next, you will need to link your MTurk Requester Account with your AWS Account. Please click [Link your AWS Account](https://console.aws.amazon.com/mechanicalturk/link-aws) and sign-in with your [AWS Root User](http://docs.aws.amazon.com/IAM/latest/UserGuide/id_root-user.html) credentials. Once your accounts are linked, you will be redirected back to this page. 

##### Step 4 Register for the MTurk Developer Sandbox.

You will need the sendbox if you want to preview your request and make sure it works as expected. Click [MTurk Developer Sandbox](https://requestersandbox.mturk.com/developer) to register and get started in the Developer Sandbox.

##### Step 5 Insert your AWS Access Key and AWS Secret Key

You could find your Keys [here](http://docs.aws.amazon.com/general/latest/gr/aws-sec-cred-types.html). After you find the keys, you could creat or insert t into com.github.mikomw/config/awsKey.secrete in following format.

[mimo]

aws_access_key_id=THIS_IS_YOUR_ACCESS_KEY

aws_secret_access_key=THIS_IS_YOUR_SECRET_KEY

##### Then you could find a sample code to build your own HITs. Everything you need to edit is in config. Hope they are easy enough to understand.

For more, you may want to see a short [tutorial]("https://www.songbo.club/2019/07/23/Evaluating-your-Dialogue-System-with-DialCrowd-and-DialMturk/").