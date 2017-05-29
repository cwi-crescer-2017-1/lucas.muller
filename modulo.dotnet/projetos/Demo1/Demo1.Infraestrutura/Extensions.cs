using System;
using System.Collections.Generic;
using System.Data.SqlClient;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace Demo1.Infraestrutura
{
    public static class Extensions
    {
        public static int? GetIntNull(this SqlDataReader dataReader, int coluna)
        {
            if (dataReader[coluna] == DBNull.Value)
                return null;
            else
                return (int)dataReader[coluna];
        }
    }
}
