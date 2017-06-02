using System;
using System.Collections.Generic;
using System.Linq;
using System.Net.Mail;
using System.Text;
using System.Threading.Tasks;

namespace EditoraCrescer.Infraestrutura.Servicos
{
    public static class EmailService
    {
        static string _smtpServer = "smtp.sendgrid.net";
        static string _smtpUserLoginName = "crescer2017";
        static string _smtpUserPassword = "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";

        public static void Enviar(string emailPara, string assunto, string corpo)
        {
            EnviarInternal("no-reply@crescer.com.br", emailPara, assunto, corpo);
        }
        public static void Enviar(string emailDe, string emailPara, string assunto, string corpo)
        {
            EnviarInternal(emailDe, emailPara, assunto, corpo);
        }

        private static void EnviarInternal(string emailDe, string emailPara, string assunto, string corpo)
        {
            return; // desativa envio de emails

            using (SmtpClient smtpClient = new SmtpClient(_smtpServer, 587))
            {
                smtpClient.Credentials = new System.Net.NetworkCredential(_smtpUserLoginName, _smtpUserPassword);
                using (MailMessage mail = new MailMessage())
                {
                    mail.From = new MailAddress(emailDe);
                    mail.To.Add(new MailAddress(emailPara));
                    mail.Body = corpo;
                    smtpClient.Send(mail);
                }
            }
        }
    }
}
